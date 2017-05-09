package vigenere;

/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Event;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextComponent;
import java.awt.TextField;
import java.util.Date;
import java.util.Random;

public class CryptVig extends Panel {
	static final int STANDARD = 0;
	static final int ASSIGNMENT = 1;
	String currentcard;
	String CYPHERTEXT;
	Random spin;
	int outcome1;
	int outcome2;
	Panel choicepanel;
	Panel keypanel;
	Panel samplepanel;
	Button plain;
	Button cypher;
	Button ncrypt;
	Button dcrypt;
	Button brake;
	Button done;
	TextArea ta;
	TextField tf;
	String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	CryptVig() {
	}

	CryptVig(int paramInt) {
		super.setBackground(new Color(100, 150, 200));
		super.setLayout(new BorderLayout(15, 15));
		super.add("Center", this.ta = new TextArea(""));
		this.choicepanel = new Panel();
		this.choicepanel.setLayout(new BorderLayout());
		this.keypanel = new Panel();
		this.keypanel.add(new Label("KEYWORD:"));
		this.keypanel.add(this.tf = new TextField(15));
		if (paramInt == 0) {
			this.samplepanel = new Panel();
			this.samplepanel.add(new Label("SAMPLE TEXTS: ", 2));
			this.samplepanel.add(this.plain = new Button("Random Plaintext"));
			this.samplepanel.add(this.cypher = new Button("Random Cyphertext"));
			this.choicepanel.add("North", this.samplepanel);

			this.keypanel.add(this.ncrypt = new Button("Encrypt"));
			this.keypanel.add(this.dcrypt = new Button("Decrypt"));

			this.spin = new Random(getranseed());
		}
		this.keypanel.add(this.brake = new Button("Break"));
		if (paramInt == 1) {
			super.setBackground(new Color(125, 125, 255));
			this.keypanel.add(this.done = new Button("Done"));
			this.ta.setEditable(false);
		}
		this.choicepanel.add("South", this.keypanel);
		super.add("South", this.choicepanel);
		this.ta.setFont(new Font("Courier", 0, 15));
	}

	private long getranseed() {
		Date localDate = new Date();
		int i = localDate.getSeconds();
		int j = localDate.getMinutes();
		long l = Math.round(Math.abs(Math.sin(i * j) * 21942134.0D));
		return l;
	}

	public String encrypt(String paramString1, String paramString2, int paramInt)
  {
    String str = "";
    int i = paramString1.length(); int j = paramString2.length();

    if (j > 0) {
      int l = 0; 
      label100:
      while (true) { 
    	  int k = (paramString1.charAt(l) - 'A' + paramInt * (paramString2.charAt(l % j) - 'A')) % 26;
    	  if (k < 0) 
    		  k += 26;
    	  str = str + this.alpha.substring(k, k + 1);

    	  ++l; 
    	  if (l >= i){
        	  return myString.prettyPrint(str); 
    	  } 
		  break label100;
      }
    }
    return myString.prettyPrint(paramString1);
  }

	public boolean action(Event paramEvent, Object paramObject) {
		if ("Encrypt".equals(paramObject)) {
			this.tf.setText(myString.Doctor(this.tf.getText()));
			this.ta.setText(encrypt(myString.Doctor(this.ta.getText()), this.tf.getText(), 1));

			return true;
		}
		if ("Decrypt".equals(paramObject)) {
			this.tf.setText(myString.Doctor(this.tf.getText()));
			this.ta.setText(encrypt(myString.Doctor(this.ta.getText()), this.tf.getText(), -1));

			return true;
		}
		if ("Random Plaintext".equals(paramObject)) {
			this.ta.setText("Selecting plaintext...");
			this.outcome1 = (int) (myString.plaintext.length * this.spin.nextDouble());
			this.ta.setText(myString.plaintext[this.outcome1]);
			this.ta.setText(myString.prettyPrint(myString.plaintext[this.outcome1]));

			return true;
		}
		if ("Random Cyphertext".equals(paramObject)) {
			this.ta.setText("Computing cyphertext...");
			this.outcome1 = (int) (myString.plaintext.length * this.spin.nextDouble());
			this.outcome2 = (int) (myString.KEYS.length * this.spin.nextDouble());
			this.ta.setText(encrypt(myString.plaintext[this.outcome1], myString.KEYS[this.outcome2], 1));

			return true;
		}

		return false;
	}
}