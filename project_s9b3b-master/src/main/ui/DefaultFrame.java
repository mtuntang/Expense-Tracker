package ui;

import model.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import static java.lang.System.exit;

public class DefaultFrame extends JFrame {
    protected static TotalExpenses total;
    String[] printOptions = {"Print All Items", "Print Groceries", "Print Entertainment", "Print Summary"};
    JLabel welcome;
    JTextArea printer;
    JButton quit;
    JButton clearData;
    JButton printInfo;
    JButton addItem;
    JComboBox comboPrint;
    JScrollPane pane;
    final String soundPath = "C:\\Users\\miket\\OneDrive\\UBC\\UBCV\\Year 2\\TERM 1\\"
            + "CPSC 210\\project_s9b3b\\src\\main\\ui\\windowsxp off sound.wav";

    public DefaultFrame(TotalExpenses totalExpenses) {
        super("Expense Tracker");
        total = totalExpenses;
        setPreferredSize((new Dimension(500, 600)));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(25, 300, 300, 300));
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Buttons
        quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total.save(total);
                playSound(soundPath);
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException a) {
                    a.printStackTrace();
                }
                exit(0);
            }
        });

        clearData = new JButton("Clear Save Data");
        clearData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total.clearData();
                printer.setText("Your save file has been deleted :)");
            }
        });

        printInfo = new JButton("Print info");
        printInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = (String) comboPrint.getSelectedItem();
                if (text.equals("Print All")) {
                    printer.setText(printAllItems(total));
                } else if (text.equals("Print Groceries")) {
                    printer.setText(printAllGroceries(total));
                } else if (text.equals("Print Entertainment")) {
                    printer.setText(printAllEatingOut(total) + "\n" + printAllRandomPurchases(total));
                } else {
                    printer.setText(printSummary(total));
                }

            }
        });

        addItem = new JButton("Add Item");
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ExpenseSelectorFrame();
            }
        });

        //Labels
        welcome = new JLabel("Welcome to my Expense Tracker Project.");
        //TextArea
        printer = new JTextArea(18, 40);
        printer.setText("");
        printer.setEditable(false);
        printer.setLineWrap(true);
        printer.setWrapStyleWord(true);
        pane = new JScrollPane(printer, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Combos
        comboPrint = new JComboBox(printOptions);
        add(welcome);
        add(comboPrint);
        add(pane);
        add(quit);
        add(clearData);
        add(printInfo);
        add(addItem);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(true);
    }

    private static class ExpenseSelectorFrame extends JFrame {
        JCheckBox category1;
        JCheckBox category2;
        JButton addGrocery;
        JButton addEntertainment;
        JLabel title;
        JLabel grocLabel;
        JLabel entLabel;

        public ExpenseSelectorFrame() {
            setPreferredSize((new Dimension(400, 300)));
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(25, 300, 300, 300));
            setLayout(new FlowLayout());

            title = new JLabel("Choose a category: ");
            grocLabel = new JLabel("Add Grocery Expense");
            entLabel = new JLabel("Add Selected Entertainment Expense");

            addGrocery = new JButton("Add Grocery");
            addGrocery.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new UserInputGrocery();
                    dispose();
                }
            });
            addEntertainment = new JButton("Add Entertainment");
            addEntertainment.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (category1.isSelected()) {
                        new UserInputEntertainment(1);
                        dispose();
                    } else {
                        new UserInputEntertainment(2);
                        dispose();
                    }

                }
            });

            category1 = new JCheckBox("Add Eating Out");
            category2 = new JCheckBox("Add Random Purchases");

            ButtonGroup categoriesGroup = new ButtonGroup();
            categoriesGroup.add(category1);
            categoriesGroup.add(category2);


            add(title);
            add(grocLabel);
            add(addGrocery);
            add(entLabel);
            add(category1);
            add(category2);
            add(addEntertainment);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(true);

        }

    }

    private static class UserInputGrocery extends JFrame {

        JLabel amount;
        JLabel description;
        JTextField amountInput;
        JTextArea descriptionInput;
        JButton addSelectedCategory;

        public UserInputGrocery() {
            super("Grocery Input");
            setPreferredSize((new Dimension(400, 400)));
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(25, 300, 300, 300));
            setLayout(new FlowLayout());
            amount = new JLabel("Input amount ($): ");
            description = new JLabel("Input description: ");
            amountInput = new JTextField(30);
            descriptionInput = new JTextArea(5, 30);
            addSelectedCategory = new JButton("Add New Expense");
            addSelectedCategory.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double money = Double.parseDouble(amountInput.getText());
                    String desc = descriptionInput.getText();
                    total.listExpense.add(new Groceries(money, desc));
                    total.save(total);
                    new NotifWindowGroc();
                    dispose();


                }
            });
            add(amount);
            add(amountInput);
            add(description);
            add(descriptionInput);
            add(addSelectedCategory);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(true);
        }
    }

    private static class UserInputEntertainment extends JFrame {
        JLabel amount;
        JLabel description;
        JTextField amountInput;
        JTextArea descriptionInput;
        JButton addSelectedCategory;

        public UserInputEntertainment(int i) {
            super("Entertainment Input");
            final int selector = i;
            setPreferredSize((new Dimension(400, 400)));
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(25, 300, 300, 300));
            setLayout(new FlowLayout());
            amount = new JLabel("Input amount ($): ");
            description = new JLabel("Input description: ");
            amountInput = new JTextField(30);
            descriptionInput = new JTextArea(5, 30);
            addSelectedCategory = new JButton("Add New Expense");
            addSelectedCategory.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double money = Double.parseDouble(amountInput.getText());
                    String desc = descriptionInput.getText();
                    if (selector == 1) {
                        total.listExpense.add(new EatingOut(money, desc));
                    } else {
                        total.listExpense.add(new RandomPurchases(money, desc));
                    }
                    total.save(total);
                    new NotifWindowEnt();
                    dispose();


                }
            });
            add(amount);
            add(amountInput);
            add(description);
            add(descriptionInput);
            add(addSelectedCategory);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(true);
        }
    }

    private static class NotifWindowGroc extends JFrame {
        JLabel notif;
        JButton ok;

        public NotifWindowGroc() {
            setPreferredSize((new Dimension(400, 200)));
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(25, 300, 300, 300));
            setLayout(new FlowLayout());
            notif = new JLabel("You've successfully added a grocery expense! ");
            ok = new JButton("OK");
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            add(notif);
            add(ok);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(true);
        }
    }

    private static class NotifWindowEnt extends JFrame {
        JLabel notif;
        JButton ok;

        public NotifWindowEnt() {
            setPreferredSize((new Dimension(400, 200)));
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(25, 300, 300, 300));
            setLayout(new FlowLayout());
            notif = new JLabel("You've successfully added an entertainment expense! ");
            ok = new JButton("OK");
            ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            add(notif);
            add(ok);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(true);
        }

    }

    //REQUIRES : sound (path to .wav file) is correct
    //EFFECTS : plays windowsXP turning off sound effect
    private void playSound(String sound) {
        try {
            File windowsXpOst = new File(sound);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(windowsXpOst));
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    //REQUIRES : TotalExpenses field
    //EFFECTS : returns a string of all items in TotalExpenses field
    public String printAllItems(TotalExpenses total) {
        return printAllGroceries(total) + (printAllEatingOut(total).equals("") ? "" : printAllEatingOut(total))
                + (printAllRandomPurchases(total).equals("") ? "" : printAllRandomPurchases(total));
    }

    //REQUIRES : TotalExpenses field
    //EFFECTS : returns a string of all Grocery type in TotalExpenses field
    public String printAllGroceries(TotalExpenses total) {
        String text = "";
        for (Expense expense : total.listExpense) {
            if (expense instanceof Groceries) {
                text += "Groceries ==> \n" + expense.getDescription() + "\n$" + expense.getMoneyOut()
                        + "\n---------------------------------\n";
            }
        }
        return text;
    }

    //REQUIRES : TotalExpenses field
    //EFFECTS : returns a string of all EatingOut type in TotalExpenses field
    public String printAllEatingOut(TotalExpenses total) {
        String text = "";
        for (Expense expense : total.listExpense) {
            if (expense instanceof EatingOut) {
                text += "Eating Out ==> \n" + expense.getDescription() + "\n$" + expense.getMoneyOut()
                        + "\n---------------------------------\n";
            }
        }
        return text;
    }

    //REQUIRES : TotalExpenses field
    //EFFECTS : returns a string of all RandomPurchases in TotalExpenses field
    public String printAllRandomPurchases(TotalExpenses total) {
        String text = "";
        for (Expense expense : total.listExpense) {
            if (expense instanceof RandomPurchases) {
                text += "Random Purchases ==> \n" + expense.getDescription() + "\n$"
                        + expense.getMoneyOut() + "\n---------------------------------\n";
            }
        }
        return text;
    }

    //REQUIRES : TotalExpenses field
    //EFFECTS : returns a string of all items in TotalExpenses field and sum of money on Groceries,
    //          Entertainment, EatingOut and RandomPurchases
    public String printSummary(TotalExpenses total) {
        String text = (printAllItems(total).equals("") ? "" : printAllItems(total) + "\n")
                + "Groceries    : $" + total.getTotalGroceries()
                + "\nEntertainment: $" + total.getTotalEntertainment()
                + "\n    Eating Out      : $" + total.getTotalEatingOut()
                + "\n    Random Purchases: $" + total.getTotalRandomPurchases()
                + "\n Total expenses: $" + total.calculateTotal();
        return text;
    }
}
