package rlbotexample;

import rlbot.manager.BotManager;
import rlbot.pyinterop.PythonInterface;
import rlbot.pyinterop.PythonServer;
import rlbotexample.util.PortReader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class JavaExample {

    private static final String VERSION = "1.0";

    public static void main(String[] args) {

        BotManager botManager = new BotManager();
        PythonInterface pythonInterface = new SamplePythonInterface(botManager);
        Integer port = PortReader.readPortFromFile("port.cfg");
        PythonServer pythonServer = new PythonServer(pythonInterface, port);
        pythonServer.start();

        JFrame frame = new JFrame("FixoBot v" + VERSION);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        
        panel.add(new JLabel("FixoBot v" + VERSION));
        panel.add(new JLabel("By DedeProGames (Credits to tarehart)"));
        panel.add(Box.createVerticalStrut(8));

        
        String portText = (port != null) ? String.valueOf(port) : "UNKNOWN";
        panel.add(new JLabel("Listening on port " + portText));
        panel.add(new JLabel("I'm the thing controlling the Java bot, keep me open!"));

        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}        JLabel botsRunning = new JLabel("Bots running: ");
        panel.add(botsRunning);
        frame.add(panel);

        frame.pack();
        frame.setVisible(true);

        ActionListener myListener = e -> {
            Set<Integer> runningBotIndices = botManager.getRunningBotIndices();
            OptionalInt maxIndex = runningBotIndices.stream().mapToInt(k -> k).max();
            String botsStr = "None";
            if (maxIndex.isPresent()) {
                StringBuilder botsStrBuilder = new StringBuilder();
                for (int i = 0; i <= maxIndex.getAsInt(); i++) {
                    botsStrBuilder.append(runningBotIndices.contains(i) ? "☑ " : "☐ ");
                }
                botsStr = botsStrBuilder.toString();
            }
            botsRunning.setText("Bots running: " + botsStr);
        };

        new Timer(1000, myListener).start();
    }
}
