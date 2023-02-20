package textproc;

import javax.swing.*;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.lang.reflect.AccessibleObject;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class BookReaderController {

    public BookReaderController (GeneralWordCounter counter){
        SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 1280, 720));
    }

    private void createWindow(GeneralWordCounter counter, String title, int width, int height){
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        frame.setSize(width,height);

        Container pane = frame.getContentPane();
        SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<>(counter.getWordList());
        JList <Map.Entry<String, Integer>> list = new JList<>(listModel);

        JPanel object = new JPanel();
        JButton searchButton = new JButton("Search");;

        JRadioButton alphabeticButton = new JRadioButton("Alphabetic", true);
        listModel.sort(new AlphabeticalComparator());
        JRadioButton frequencyButton = new JRadioButton("Frequency", false);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(alphabeticButton);
        buttonGroup.add(frequencyButton);

        alphabeticButton.addActionListener(e -> listModel.sort(new AlphabeticalComparator()));
        frequencyButton.addActionListener(e -> listModel.sort(new WordCountComparator()));

        JTextField textField = new JTextField(20);
        Action findTypedWord = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText().toLowerCase();
                for (int i = 0; i < listModel.getSize(); i++) {
                    String s = listModel.getElementAt(i).getKey();
                    if (s.equals(text)){
                        list.ensureIndexIsVisible(i);
                        list.setSelectedIndex(i);
                        return;
                    }
                }

                JOptionPane.showMessageDialog(frame, "The specified word does not exist", "Error", JOptionPane.ERROR_MESSAGE);
            }
        };

        searchButton.addActionListener(findTypedWord);
        searchButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).
                put(KeyStroke.getKeyStroke((KeyEvent.VK_ENTER), 0), "enter");
        searchButton.getActionMap().put("enter",  findTypedWord);

        object.add(alphabeticButton);
        object.add(frequencyButton);
        object.add(textField);
        object.add(searchButton);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pane.add(scrollPane);
        pane.add(object, BorderLayout.SOUTH);

        //frame.pack();
        frame.setVisible(true);
    }
}
