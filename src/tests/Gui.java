package tests;

import Factory.DwellingFactory;
import buildings.Buildings;
import buildings.Interfaces.Building;
import buildings.Interfaces.Floor;
import buildings.Interfaces.Space;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class Gui extends JFrame {

    private Container container;
    private JTextPane textPane;
    private JScrollPane scrollMain;

    public Gui() {
        super("Gui");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setDefaultLookAndFeelDecorated(true);

        container = getContentPane();
        textPane = new JTextPane();

        textPane.setEditable(false);
        textPane.setText("Space...");

        container.add(new JLabel("Welcome!"), BorderLayout.NORTH);
        container.add(textPane, BorderLayout.SOUTH);
        setVisible(true);

        Font font = new Font("Verdana", Font.PLAIN, 14);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Menu");
        fileMenu.setFont(font);

        JMenuItem openDwellingItem = new JMenuItem("Open dwelling");
        openDwellingItem.setFont(font);
        openDwellingItem.addActionListener((event) -> {
            JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fc.showOpenDialog(container);
            if (result == JFileChooser.APPROVE_OPTION) {
                try (FileReader in = new FileReader(fc.getSelectedFile())) {
                    Buildings.setBuildingFactory(new DwellingFactory());
                    Building building = Buildings.readBuilding(in);
                    createJPanelFloors(building);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error reading building file");
                }
            }
        });
        fileMenu.add(openDwellingItem);

        JMenuItem openOfficeBuildingItem = new JMenuItem("Open office building");
        openOfficeBuildingItem.setFont(font);
        openOfficeBuildingItem.addActionListener((event) -> {
            JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fc.showOpenDialog(container);
            if (result == JFileChooser.APPROVE_OPTION) {
                try (FileReader in = new FileReader(fc.getSelectedFile())) {
                    Buildings.setBuildingFactory(new DwellingFactory());
                    Building building = Buildings.readBuilding(in);
                    createJPanelFloors(building);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error reading building file");
                }
            }
        });
        fileMenu.add(openOfficeBuildingItem);

        fileMenu.addSeparator();

        JMenu lookFeel = new JMenu("Look & Feel");
        lookFeel.setFont(font);
        fileMenu.add(lookFeel);

        ButtonGroup buttonGroup = new ButtonGroup();
        UIManager.LookAndFeelInfo[] styles = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo style : styles) {
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(style.getName());
            item.setFont(font);
            item.addActionListener((ActionEvent e) -> {
                try {
                    UIManager.setLookAndFeel(style.getClassName());
                    SwingUtilities.updateComponentTreeUI(getContentPane());
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
                    JOptionPane.showMessageDialog(null, "Error");
                }
            });
            buttonGroup.add(item);
            lookFeel.add(item);
        }

        fileMenu.addSeparator();

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.setFont(font);
        fileMenu.add(exitItem);

        exitItem.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        revalidate();
    }

    public void createJPanelFloors(Building building) {
        JPanel panel = new JPanel(new VerticalLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        for (int i = 0; i < building.getSumFloorCount(); i++) {
            JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
            Button floorButton = new Button("Floor: " + i);
            Floor floor = building.getFloor(i);
            floorButton.addActionListener((event) -> {
                textPane.setText(floor.toString());
            });
            flow.add(floorButton);
            panel.add(flow);
            container.add(panel, BorderLayout.CENTER);
            for (int j = 0; j < building.getFloor(i).getSpaceCount(); j++) {
                Button button = new Button(String.valueOf(j));
                flow.add(button);
                Space space = building.getFloor(i).getSpace(j);
                button.addActionListener((event) -> {
                    textPane.setText(space.toString());
                });
            }
        }
        scrollMain = new JScrollPane(panel);
        container.add(scrollMain);
        revalidate();
    }

    public void initMetalLookAndFeel() {
        try {
            MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Can't use the specified look and feel on this platform.");
        } catch (Exception e) {
            System.err.println("Couldn't get specified look and feel, for some reason.");
        }
    }

    public static void main(String[] args) {
        new Gui();
    }
}
