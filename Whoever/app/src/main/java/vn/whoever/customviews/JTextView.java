package vn.whoever.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
    private ArrayList<Line> linecollection;
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
        linecollection = new ArrayList<Line>();
        this.text = text;
        init();
    }

    public void init() {
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textColor = Color.BLACK;
    }

    @Override
    protected void onMeasure(int wMeasureSpace, int hMeasureSpace) {
        super.onMeasure(wMeasureSpace, hMeasureSpace);

        if(font != null) {
            font = Typeface.createFromAsset(getContext().getAssets(), "fonts/arial.ttf" );
            textPaint.setTypeface(font);
        }

        textPaint.setColor(textColor);

        int minnw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        width = resolveSizeAndState(minnw, wMeasureSpace, 1);
        height = MeasureSpec.getSize(wMeasureSpace);

        onBirim = 0.009259259f * width;
        leftPadding = 3 * onBirim + getPaddingLeft();
        rightPadding = 3 * onBirim + getPaddingRight();
        lineHeight = textSize + lineSpace;

        textPaint.setTextSize(textSize);

        wordSpace = 15f;
        Line lineBuffer = new Line();
        this.linecollection.clear();
        String[] lines = text.split("\n");
        for(String line : lines) {
            String[] words = line.split(" ");
            lineBuffer = new Line();
            float lineWidth = leftPadding + rightPadding;
            float totalWordWidth = 0;

            for(String word : words) {
                float wordword = textPaint.measureText(word) + wordSpace;

                
            }
        }
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

    public ArrayList<Line> getLinecollection() {
        return linecollection;
    }

    public void setLinecollection(ArrayList<Line> lines) {
        this.linecollection = lines;
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