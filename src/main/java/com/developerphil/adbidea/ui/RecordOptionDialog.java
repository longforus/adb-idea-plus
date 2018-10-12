package com.developerphil.adbidea.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;

public class RecordOptionDialog extends JDialog {
    private JPanel          contentPane;
    private JButton         buttonOK;
    private JButton         buttonCancel;
    private JCheckBox       mShowCheckBox;
    private JSlider         mSliderTime;
    @NotNull
    public  Function2<Boolean,Integer,Unit> okListener;

    public RecordOptionDialog(@NotNull Function2<Boolean,Integer,Unit> okListener) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.okListener = okListener;


        mSliderTime.setMajorTickSpacing(10);

        mSliderTime.setMinorTickSpacing(2);

        mSliderTime.setPaintTicks(true);
        mSliderTime.setPaintLabels(true);


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        okListener.invoke(mShowCheckBox.isSelected(), mSliderTime.getValue());
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}