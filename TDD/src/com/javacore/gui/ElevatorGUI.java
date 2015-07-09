package com.javacore.gui;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import com.javacore.controllers.ElevatorController;
import com.javacore.interfaces.ElevatorObserver;
import com.javacore.utils.Constants;
import com.javacore.utils.GlobalLog;
import com.javacore.utils.currentWay;
/** Swing GUI Class */
public class ElevatorGUI extends JFrame implements ElevatorObserver {
	private static final long serialVersionUID = 1L;
	private ElevatorController elevatorContr;
	private int currFloor;
	private Enum<currentWay> currWay;
	private boolean isRun;
	private boolean isTerminate;
	private boolean isComplete;
	private JButton button2;
	private JTextArea textArea2;
	private JSeparator separator2;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	
	public ElevatorGUI(ElevatorController elevatorController) throws InterruptedException {
		this.elevatorContr = elevatorController;
		this.isRun = false;
		this.isTerminate = false;
		this.isComplete = false;
		this.currFloor = elevatorContr.getCurrentFloor();
		this.currWay = elevatorContr.getCurrWay();
		initComponents();
	}

	private void button1ActionPerformed(ActionEvent e) throws IOException {
		if(!isRun && !isTerminate && !isComplete){
			button2.setText(Constants.ABORT);
			button2.paintImmediately(button2.getVisibleRect());
			isRun = true;
				new Thread() {
                   public void run() {
                	   try {
						elevatorContr.runElevator();
					} catch (InterruptedException e) {
					}
				   }
                 }.start();
		}else{
			if(isRun && !isTerminate && !isComplete){
				elevatorContr.setTerminate();
				isTerminate = true;
				button2.setText(Constants.VIEW_LOG);
				button2.paintImmediately(button2.getVisibleRect());
			}else{
				Desktop.getDesktop().open(new File(Constants.LOG_FILE));
			}
		}
	}
	
	private void initComponents() throws InterruptedException {
		button2 = new JButton();
		textArea2 = new JTextArea();
		JScrollPane scroll = new JScrollPane (textArea2);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		separator2 = new JSeparator();
		label4 = new JLabel();
		label5 = new JLabel();
		label6 = new JLabel();
		setTitle("     Elevator Control Panel");
		Container contentPane = getContentPane();
		button2.setText(Constants.START);
		button2.setFont(new Font("Tahoma", Font.BOLD, 22));
		button2.setForeground(Color.darkGray);
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					button1ActionPerformed(e);
				} catch (IOException e1) {
				}
			}
		});
		label5.setText(String.valueOf(currFloor));
		if(currWay.equals(currentWay.UP)){
			label6.setText(Constants.UP);
		}else{
			label6.setText(Constants.DOWN);
		}
		label4.setText(Constants.FLOOR);
		label4.setFont(new Font("Tahoma", Font.BOLD, 26));
		label4.setForeground(Color.darkGray);
		label5.setFont(new Font("Square721 BT", Font.BOLD, 88));
		label5.setForeground(new Color(0, 153, 51));
		label6.setFont(new Font("Tahoma", Font.BOLD, 36));
		label6.setForeground(new Color(0, 153, 51));

		GroupLayout contentPaneLayout = new GroupLayout(contentPane);
		contentPane.setLayout(contentPaneLayout);
		contentPaneLayout.setHorizontalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(contentPaneLayout.createSequentialGroup()
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(contentPaneLayout.createParallelGroup()
								.addGroup(contentPaneLayout.createSequentialGroup()
									.addComponent(label4, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
									.addGap(6, 6, 6)
									.addComponent(label5, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
									.addGap(11, 11, 11)
									.addComponent(label6, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))
								.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)
								.addComponent(separator2, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE)))
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGap(76, 76, 76)
							.addComponent(button2, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPaneLayout.setVerticalGroup(
			contentPaneLayout.createParallelGroup()
				.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
					.addContainerGap(26, Short.MAX_VALUE)
					.addGroup(contentPaneLayout.createParallelGroup()
						.addGroup(contentPaneLayout.createSequentialGroup()
							.addGap(19, 19, 19)
							.addComponent(label4, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
						.addComponent(label5, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addComponent(label6, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
					.addGap(7, 7, 7)
					.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addGap(6, 6, 6)
					.addComponent(separator2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(button2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		pack();
		setLocationRelativeTo(getOwner());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        if(elevatorContr.getAnimationBoost() > 0){
        this.setVisible(true);
        }
	}
	
	@Override
	public void update() {
        currFloor = elevatorContr.getCurrentFloor();
		currWay = elevatorContr.getCurrWay();
		textArea2.setText(GlobalLog.getLog());
		//textArea2.paintImmediately(textArea2.getVisibleRect());
		if(elevatorContr.isComplete()){
			isComplete = elevatorContr.isComplete();
			button2.setText(Constants.VIEW_LOG);
			button2.paintImmediately(button2.getVisibleRect());
			if(elevatorContr.getAnimationBoost() == 0){
				this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
			}
		}
		label5.setText(String.valueOf(currFloor));
		label5.paintImmediately(label5.getVisibleRect());
		if(currWay.equals(currentWay.UP)){
			label6.setText(Constants.UP);
			label6.paintImmediately(label6.getVisibleRect());
		}else{
			label6.setText(Constants.DOWN);
			label6.paintImmediately(label6.getVisibleRect());
		}
		
	}
	
}
