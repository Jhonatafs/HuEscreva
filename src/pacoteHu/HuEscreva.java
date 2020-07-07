package pacoteHu;

import java.awt.EventQueue;

public class HuEscreva {
    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    new Janela();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
}
