package com.example.alainbansais.characters;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alainbansais.marvel.R;
import com.example.alainbansais.model.Character;
import com.squareup.picasso.Picasso;

import java.lang.*;

public class DisplayDetailsActivity extends AppCompatActivity {

    public final static String CHARACTER_EXTRA = "character_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_details);
        ImageView viewTest = (ImageView) findViewById(R.id.img_zoom);
        TextView viewName = (TextView) findViewById(R.id.character_name);
        TextView viewDescription = (TextView) findViewById(R.id.character_description);

        Bundle characterParcelableReference = getIntent().getExtras();
        Character characterItemParcelable =
                characterParcelableReference.getParcelable(CHARACTER_EXTRA);
        if (characterItemParcelable != null) {
            //noinspection ConstantConditions
            viewName.setText(characterItemParcelable.getNickname());
            //noinspection ConstantConditions
            viewDescription.setText(characterItemParcelable.getDescription());
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            //noinspection ConstantConditions
            viewTest.getLayoutParams().height = (int) ((((float) width / 3f)) * 2);
            viewTest.requestLayout();
            Picasso.with(this)
                   .load(characterItemParcelable.getBigSize())
                   .into(viewTest);
        }
    }
}
