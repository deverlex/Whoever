package vn.whoever.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by spider man on 1/17/2016.
 */
public class JTextView extends View {

    private String text;
    private ArrayList<Line> lines;
    private TextPaint textPaint;
    private Typeface font;
    private int textColor;

    private float textSize;
    private float lineHeight;
    private float wordSpace;
    private float lineSpace;

    private float onBirim;
    private float width;
    private float height;
    private float leftPadding;
    private float rightPadding;

    public JTextView(Context context, String text) {
        super(context);
        lines = new ArrayList<Line>();
        this.text = text;
    }

    public void init() {

    }

    @Override
    protected void onMeasure(int wMeasureSpace, int hMeasureSpace) {
        super.onMeasure(wMeasureSpace, hMeasureSpace);
        
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    public void setLines(ArrayList<Line> lines) {
        this.lines = lines;
    }

    public TextPaint getTextPaint() {
        return textPaint;
    }

    public void setTextPaint(TextPaint textPaint) {
        this.textPaint = textPaint;
    }

    public Typeface getFont() {
        return font;
    }

    public void setFont(Typeface font) {
        this.font = font;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getWordSpace() {
        return wordSpace;
    }

    public void setWordSpace(float wordSpace) {
        this.wordSpace = wordSpace;
    }

    public float getLineHeight() {
        return lineHeight;
    }

    public void setLineHeight(float lineHeight) {
        this.lineHeight = lineHeight;
    }

    public float getLineSpace() {
        return lineSpace;
    }

    public void setLineSpace(float lineSpace) {
        this.lineSpace = lineSpace;
    }

    public float getLeftPadding() {
        return leftPadding;
    }

    public void setLeftPadding(float leftPadding) {
        this.leftPadding = leftPadding;
    }

    public float getRightPadding() {
        return rightPadding;
    }

    public void setRightPadding(float rightPadding) {
        this.rightPadding = rightPadding;
    }

    class Line {

        private ArrayList<String> words;
        private float space = 12f;

        public Line() {
            words = new ArrayList<String>();
        }

        public Line(ArrayList<String> words, float space) {
            this.words = words;
            this.space = space;
        }

        public ArrayList<String> getWords() {
            return words;
        }

        public void setWords(ArrayList<String> words) {
            this.words = words;
        }

        public float getSpace() {
            return space;
        }

        public void setSpace(float space) {
            this.space = space;
        }

        public void addWord(String str) {
            this.words.add(str);
        }
    }
}