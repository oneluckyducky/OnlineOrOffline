package pro.geektalk.onlineoroffline;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import pro.geektalk.onlineoroffline.gui.Gui;

public class OnlineOrOffline {

	public static void main(String[] ss) {

		for (UIManager.LookAndFeelInfo info : UIManager
				.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}

		try {
			EventQueue.invokeLater(new Runnable() {

				@Override
				public void run() {
					new Gui();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
