package vn.whoever.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by spider man on 1/17/2016.
 */
public class JTextView extends View {

    private String text;

    public JTextView(Context context, AttributeSet attr) {
        super(context);
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
