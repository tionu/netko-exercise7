package vigenere;

/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public class VigStatsPanel extends Panel {
	Graphics g;
	Image i;
	String KEY;
	String SAMPLETEXT;
	int[] cstats;
	String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	int position;
	int xnot;
	int ynot;
	float[] english = { 73.0F, 9.0F, 30.0F, 44.0F, 130.0F, 28.0F, 16.0F, 35.0F, 74.0F, 2.0F, 3.0F, 35.0F, 25.0F, 78.0F,
			74.0F, 27.0F, 3.0F, 77.0F, 63.0F, 93.0F, 27.0F, 13.0F, 16.0F, 5.0F, 19.0F, 1.0F };
	float[] german = { 65.0F, 19.0F, 31.0F, 51.0F, 174.0F, 17.0F, 30.0F, 48.0F, 76.0F, 3.0F, 12.0F, 34.0F, 25.0F, 98.0F,
			25.0F, 8.0F, 1.0F, 70.0F, 73.0F, 62.0F, 44.0F, 7.0F, 19.0F, 1.0F, 1.0F, 11.0F};

	VigStatsPanel() {
		super.setBackground(Color.white);

		this.position = 1;

		this.cstats = new int[26];
		for (int j = 0; j < 26; ++j)
			this.cstats[j] = 0;
	}

	public void update(Graphics paramGraphics) {
		paint(paramGraphics);
	}

	public void paint(Graphics paramGraphics) {
		int i1 = super.size().width;
		int i2 = super.size().height;
		int i3 = i2 - 85;
		double d = i1 / 27.0D;

		this.g.setPaintMode();
		this.g.setColor(Color.white);
		this.g.fillRect(0, 0, i1, i2);
		this.g.setColor(Color.black);
		this.g.drawRect(0, 0, i1 - 1, i2 - 1);
		this.g.drawRect(0, 0, i1 - 1, i2 - 35);
		int j;
		for (int i4 = 1; i4 < 27; ++i4) {
			j = (int) (i4 * d);

			this.g.setColor(Color.blue);
			this.g.fillRect(j - (int) (d / 8.0D), i2 - 35 - (int) (i3 * this.german[(i4 - 1)] / 130.0F),
					(int) (d / 2.0D), (int) (i3 * this.german[(i4 - 1)] / 130.0F));
			String str1 = this.ALPHA.substring(i4 - 1, i4);
			this.g.drawString(str1, j - (this.g.getFontMetrics().stringWidth(str1) / 2), i2 - 5);
			this.g.setColor(Color.black);
			this.g.drawRect(j - (int) (d / 8.0D), i2 - 35 - (int) (i3 * this.german[(i4 - 1)] / 130.0F),
					(int) (d / 2.0D), (int) (i3 * this.german[(i4 - 1)] / 130.0F));

			int l = (i4 - 1 + this.KEY.charAt(this.position - 1) - 65) % 26;
			this.g.setColor(Color.red);
			str1 = this.ALPHA.substring(l, l + 1);
			this.g.drawString(str1, j - (this.g.getFontMetrics().stringWidth(str1) / 2), i2 - 21);
			if (this.cstats[l] > 130) {
				this.g.fillRect(j - (int) (3.0D * d / 8.0D), i2 - 35 - i3, (int) (d / 2.0D), i3);
				this.g.setColor(Color.black);
				this.g.drawRect(j - (int) (3.0D * d / 8.0D), i2 - 35 - i3, (int) (d / 2.0D), i3);
				this.g.drawString("+", j - (int) (d / 4.0D) - 1, 47);
			} else {
				this.g.fillRect(j - (int) (3.0D * d / 8.0D), i2 - 35 - (int) (i3 * this.cstats[l] / 130.0F),
						(int) (d / 2.0D), (int) (i3 * this.cstats[l] / 130.0F));
				this.g.setColor(Color.black);
				this.g.drawRect(j - (int) (3.0D * d / 8.0D), i2 - 35 - (int) (i3 * this.cstats[l] / 130.0F),
						(int) (d / 2.0D), (int) (i3 * this.cstats[l] / 130.0F));
			}

		}

		this.g.setColor(Color.black);
		int k = this.g.getFontMetrics().stringWidth("KEYWORD");
		this.g.drawString("KEYWORD", i1 / 4 - (k / 2), 15);
		this.g.drawLine(i1 / 4 - (k / 2), 16, i1 / 4 + k / 2, 16);
		k = i1 / 4 - (this.g.getFontMetrics().stringWidth(this.KEY) / 2);
		for (int i5 = 0; i5 < this.KEY.length(); ++i5) {
			this.g.setColor(Color.black);
			if (i5 == this.position - 1)
				this.g.setColor(Color.red);
			this.g.drawString(this.KEY.substring(i5, i5 + 1), k, 30);
			k += this.g.getFontMetrics().stringWidth(this.KEY.substring(i5, i5 + 1));
		}

		this.g.setColor(Color.black);
		k = this.g.getFontMetrics().stringWidth("SAMPLETEXT");
		this.g.drawString("SAMPLETEXT", 3 * i1 / 4 - (k / 2), 15);
		this.g.drawLine(3 * i1 / 4 - (k / 2), 16, 3 * i1 / 4 + k / 2, 16);

		String str2 = "";
		for (int i6 = 0; i6 < this.SAMPLETEXT.length(); ++i6) {
			j = (this.SAMPLETEXT.charAt(i6) - this.KEY.charAt(i6 % this.KEY.length())) % 26;
			if (j < 0)
				j += 26;
			str2 = str2 + this.ALPHA.substring(j, j + 1);
		}
		k = 3 * i1 / 4 - (this.g.getFontMetrics().stringWidth(str2) / 2);
		for (int i7 = 0; i7 < str2.length(); ++i7) {
			this.g.setColor(Color.black);
			if (i7 % this.KEY.length() == this.position - 1)
				this.g.setColor(Color.red);
			this.g.drawString(str2.substring(i7, i7 + 1), k, 30);
			k += this.g.getFontMetrics().stringWidth(str2.substring(i7, i7 + 1));
		}

		paramGraphics.drawImage(this.i, 0, 0, this);
	}

	public void editKey(int paramInt, String paramString) {
		this.KEY = this.KEY.substring(0, paramInt) + paramString.toUpperCase() + this.KEY.substring(paramInt + 1);
	}

	public boolean keyDown(Event paramEvent, int paramInt) {
		int j;
		if ((paramInt >= 65) && (paramInt <= 90)) {
			j = paramInt - 65 - 4; // -4 ggf herausnehmen
			if (j < 0)
				j += 26;
			editKey(this.position - 1, this.ALPHA.substring(j, j + 1));
		} else if ((paramInt >= 97) && (paramInt <= 122)) {
			j = paramInt - 97 - 4;
			if (j < 0)
				j += 26;
			editKey(this.position - 1, this.ALPHA.substring(j, j + 1));
		} else if (paramInt == 32) {
			j = (this.KEY.charAt(this.position - 1) - 'A' + 1) % 26;
			editKey(this.position - 1, this.ALPHA.substring(j, j + 1));
		}
		super.repaint();
		return true;
	}

	public boolean mouseDown(Event paramEvent, int paramInt1, int paramInt2) {
		this.xnot = paramInt1;
		this.ynot = paramInt2;
		return true;
	}

	public boolean mouseDrag(Event paramEvent, int paramInt1, int paramInt2) {
		int k = super.size().width / 27;
		if ((this.ynot < 50) || (this.ynot > super.size().height - 35))
			return true;
		int j;
		if (paramInt1 - this.xnot > k) {
			j = (this.KEY.charAt(this.position - 1) - 'A' - ((paramInt1 - this.xnot) / k)) % 26;
			if (j < 0)
				j += 26;
			editKey(this.position - 1, this.ALPHA.substring(j, j + 1));
			this.xnot += k * (paramInt1 - this.xnot) / k;
			super.repaint();
			return true;
		}

		if (this.xnot - paramInt1 > k) {
			j = (this.KEY.charAt(this.position - 1) - 'A' - ((paramInt1 - this.xnot) / k)) % 26;
			if (j < 0)
				j += 26;
			editKey(this.position - 1, this.ALPHA.substring(j, j + 1));
			this.xnot -= k * (this.xnot - paramInt1) / k;
			super.repaint();
			return true;
		}

		return true;
	}
}