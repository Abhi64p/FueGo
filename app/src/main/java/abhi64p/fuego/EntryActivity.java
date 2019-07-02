package abhi64p.fuego;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EntryActivity extends AppCompatActivity
{
    private View OuterCircle, OuterCircle2;
    private ValueAnimator VA, VA2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        OuterCircle = findViewById(R.id.StartOuterCircleIV);
        OuterCircle2 = findViewById(R.id.StartOuterCircle2IV);
        DoAnimation();
    }

    public void StartPressed(final View view)
    {
        VA.end();
        OuterCircle.setScaleX(1);
        OuterCircle.setScaleY(1);
        OuterCircle.setAlpha(1);

        VA2.end();
        OuterCircle2.setScaleX(1);
        OuterCircle2.setScaleY(1);
        OuterCircle2.setAlpha(1);

        startActivityForResult(new Intent(EntryActivity.this,BurnerNobActivity.class),0);
    }

    private void DoAnimation()
    {
        VA = new ValueAnimator();
        VA.setRepeatMode(ValueAnimator.RESTART);
        VA.setRepeatCount(ValueAnimator.INFINITE);
        VA.setDuration(1000);
        VA.setFloatValues(1,1.5f);
        VA.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                float val = (float) valueAnimator.getAnimatedValue();
                OuterCircle.setScaleX(val);
                OuterCircle.setScaleY(val);
                OuterCircle.setAlpha(1.5f-val);
            }
        });
        VA.start();

        VA2 = new ValueAnimator();
        VA2.setRepeatMode(ValueAnimator.RESTART);
        VA2.setRepeatCount(ValueAnimator.INFINITE);
        VA2.setDuration(1000);
        VA2.setFloatValues(1,1.5f);
        VA2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator)
            {
                float val = (float) valueAnimator.getAnimatedValue();
                OuterCircle2.setScaleX(val);
                OuterCircle2.setScaleY(val);
                OuterCircle2.setAlpha(1.5f-val);
            }
        });
        VA2.setStartDelay(300);
        VA2.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        this.finish();
    }
}
