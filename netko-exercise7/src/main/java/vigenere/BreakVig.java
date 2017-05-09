package vigenere;

/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Event;
import java.awt.Label;
import java.awt.Panel;

public class BreakVig extends Panel {
	Button up;
	Button down;
	Button left;
	Button right;
	Button apply;
	Panel buttonpanel;
	int maxperiod = 20;
	String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String cyphertext;
	String KEY;
	String newkey;
	VigStatsPanel vigstatspanel;

	BreakVig() {
		super.setBackground(new Color(100, 150, 200));
		super.setLayout(new BorderLayout());
		this.buttonpanel = new Panel();
		this.buttonpanel.add(new Label("Period:"));
		this.buttonpanel.add(this.up = new Button("+"));
		this.buttonpanel.add(this.down = new Button("-"));
		this.buttonpanel.add(new Label("      Position:"));
		this.buttonpanel.add(this.left = new Button("Left"));
		this.buttonpanel.add(this.right = new Button("Right"));
		this.buttonpanel.add(new Label("      "));
		this.buttonpanel.add(this.apply = new Button("Decrypt"));
		super.add("South", this.buttonpanel);
		super.add("Center", this.vigstatspanel = new VigStatsPanel());

		this.newkey = "";
		for (int i = 0; i < this.maxperiod; ++i)
			this.newkey += "A";

		this.down.disable();
		this.left.disable();
		this.right.disable();
	}

	public boolean action(Event paramEvent, Object paramObject) {
		int i = this.vigstatspanel.KEY.length();
		if (paramEvent.target instanceof Button) {
			if (("+".equals(paramObject)) && (i != this.maxperiod)) {
				this.down.enable();
				this.left.disable();
				this.right.enable();
				if (i + 1 == this.maxperiod)
					this.up.disable();
				this.vigstatspanel.position = 1;
				this.vigstatspanel.KEY = this.newkey.substring(0, i + 1);
				this.vigstatspanel.cstats = statistic(this.cyphertext, i + 1, 1);
				this.vigstatspanel.requestFocus();
				this.vigstatspanel.repaint();
				return true;
			}
			if (("-".equals(paramObject)) && (i != 1)) {
				this.up.enable();
				this.left.disable();
				this.right.enable();
				if (i - 1 == 1) {
					this.down.disable();
					this.right.disable();
				}
				this.vigstatspanel.position = 1;
				this.vigstatspanel.KEY = this.newkey.substring(0, i - 1);
				this.vigstatspanel.cstats = statistic(this.cyphertext, i - 1, 1);
				this.vigstatspanel.requestFocus();
				this.vigstatspanel.repaint();
				return true;
			}
			if (("Left".equals(paramObject)) && (this.vigstatspanel.position != 1)) {
				this.right.enable();
				if (--this.vigstatspanel.position == 1)
					this.left.disable();
				this.vigstatspanel.cstats = statistic(this.cyphertext, i, this.vigstatspanel.position);
				this.vigstatspanel.requestFocus();
				this.vigstatspanel.repaint();
				return true;
			}
			if (("Right".equals(paramObject)) && (this.vigstatspanel.position != i)) {
				this.left.enable();
				if (++this.vigstatspanel.position == i)
					this.right.disable();
				this.vigstatspanel.cstats = statistic(this.cyphertext, i, this.vigstatspanel.position);
				this.vigstatspanel.requestFocus();
				this.vigstatspanel.repaint();
				return true;
			}
			return false;
		}
		return false;
	}

	public int[] statistic(String paramString, int paramInt1, int paramInt2) {
		int i = 0;
		int[] arrayOfInt = new int[26];
		String str = myString.Doctor(paramString);
		int j = str.length();

		if (paramInt2 <= j % paramInt1)
			i = 1;
		int k = j / paramInt1 + i;

		for (int l = 0; l < 26; ++l)
			arrayOfInt[l] = 0;
		for (int i1 = 0; i1 < k; ++i1)
			arrayOfInt[(str.charAt(i1 * paramInt1 + paramInt2 - 1) - 'A')] += 1;

		if (k > 0)
			for (int i2 = 0; i2 < 26; ++i2)
				arrayOfInt[i2] = (1000 * arrayOfInt[i2] / k);

		return arrayOfInt;
	}
}