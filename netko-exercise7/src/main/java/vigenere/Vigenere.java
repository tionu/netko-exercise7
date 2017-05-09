package vigenere;

/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Event;
import java.awt.Image;
import java.awt.Panel;
import java.awt.TextComponent;

public class Vigenere extends Applet {
	CryptVig cryptvig;
	BreakVig breakvig;
	Panel cards;
	String currentcard;
	String CYPHERTEXT;

	public void init() {
		this.cryptvig = new CryptVig(0);
		this.breakvig = new BreakVig();
		this.breakvig.vigstatspanel.i = super.createImage(800, 640);
		this.breakvig.vigstatspanel.g = this.breakvig.vigstatspanel.i.getGraphics();

		super.setBackground(new Color(100, 150, 200));
		super.setLayout(new BorderLayout());
		this.cards = new Panel();
		this.cards.setLayout(new CardLayout(15, 15));
		this.cards.add("VIGENERE", this.cryptvig);
		this.cards.add("BREAKVIG", this.breakvig);
		super.add("Center", this.cards);

		this.currentcard = "VIGENERE";
		((CardLayout) this.cards.getLayout()).show(this.cards, this.currentcard);

		this.CYPHERTEXT = "";
		super.repaint();
	}

	public boolean action(Event paramEvent, Object paramObject) {
		String str;
		if (this.currentcard == "VIGENERE") {
			if ("Break".equals(paramObject)) {
				this.currentcard = "BREAKVIG";
				((CardLayout) this.cards.getLayout()).show(this.cards, this.currentcard);

				str = myString.Doctor(this.cryptvig.tf.getText());
				if (str.length() > this.breakvig.maxperiod)
					str = str.substring(0, this.breakvig.maxperiod);
				if (str.length() <= 0)
					str = "A";
				this.breakvig.vigstatspanel.position = 1;
				this.breakvig.vigstatspanel.KEY = str;

				this.breakvig.up.disable();
				this.breakvig.down.enable();
				this.breakvig.left.disable();
				this.breakvig.right.enable();
				if (str.length() < this.breakvig.maxperiod)
					this.breakvig.up.enable();
				if (str.length() == 1) {
					this.breakvig.down.disable();
					this.breakvig.right.disable();
				}

				this.CYPHERTEXT = myString.Doctor(this.cryptvig.ta.getText());
				this.breakvig.cyphertext = this.CYPHERTEXT;
				this.breakvig.vigstatspanel.SAMPLETEXT = this.CYPHERTEXT.substring(0,
						Math.min(25, this.CYPHERTEXT.length()));
				this.breakvig.vigstatspanel.cstats = this.breakvig.statistic(this.CYPHERTEXT, str.length(), 1);
				this.breakvig.vigstatspanel.repaint();
				return true;
			}
			return this.cryptvig.action(paramEvent, paramObject);
		}
		if (this.currentcard == "BREAKVIG") {
			if ("Decrypt".equals(paramObject)) {
				this.currentcard = "VIGENERE";
				((CardLayout) this.cards.getLayout()).show(this.cards, "VIGENERE");
				str = this.breakvig.vigstatspanel.KEY;
				this.cryptvig.tf.setText(str);
				this.cryptvig.ta.setText("Computing plaintext...");
				this.cryptvig.ta.setText(this.cryptvig.encrypt(this.CYPHERTEXT, str, -1));
				return true;
			}
			return this.breakvig.action(paramEvent, paramObject);
		}

		return super.handleEvent(paramEvent);
	}

	public boolean keyDown(Event paramEvent, int paramInt) {
		if (this.currentcard == "BREAKVIG") {
			return this.breakvig.vigstatspanel.keyDown(paramEvent, paramInt);
		}
		return super.keyDown(paramEvent, paramInt);
	}
}