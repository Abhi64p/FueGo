package abhi64p.fuego;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CookingActivity extends AppCompatActivity
{
    private View[] v;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking);

        v = new View[7];
        TextView Title = findViewById(R.id.CookingActivityTitleTV);

        v[0] = findViewById(R.id.v1);
        v[1] = findViewById(R.id.v2);
        v[2] = findViewById(R.id.v3);
        v[3] = findViewById(R.id.v4);
        v[4] = findViewById(R.id.v5);
        v[5] = findViewById(R.id.v6);
        v[6] = findViewById(R.id.v7);

        Intent intent = getIntent();
        String BurnerText = intent.getStringExtra("BurnerText");
        String ModeText = intent.getStringExtra("ModeText");

        Title.setText(String.format("%s -> %s",BurnerText,ModeText));

        int StartDelay = 0;
        for(int i=0; i<7; i++, StartDelay += 400)
            DoAnimation(i,StartDelay);
    }


    private void DoAnimation(final int i, int StartDelay)
    {
        ValueAnimator animator = new ValueAnimator();
        animator.setFloatValues(1,0.5f);
        animator.setDuration(800);
        animator.setStartDelay(StartDelay);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                v[i].setAlpha((float)animation.getAnimatedValue());
            }
        });
        animator.start();
    }
}
