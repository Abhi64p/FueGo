package abhi64p.fuego;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import static abhi64p.fuego.BurnerNobActivity.IP;

public class EntryActivity extends AppCompatActivity
{
    private View OuterCircle, OuterCircle2;
    private ValueAnimator VA, VA2;
    private TextView CurrIP;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        OuterCircle = findViewById(R.id.StartOuterCircleIV);
        OuterCircle2 = findViewById(R.id.StartOuterCircle2IV);
        DoAnimation();

        IP = getSharedPreferences(getApplicationContext().getPackageName(),MODE_PRIVATE).getString("IP","0.0.0.0");

        CurrIP = findViewById(R.id.CurrentIPTV);
        String CurrIPStr = "Current IP : " + IP;
        CurrIP.setText(CurrIPStr);
    }

    public void StartPressed(final View view)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                SendData SD = new SendData(IP+"/start");
                SD.setListener(new SendData.SendListener()
                {
                    @Override
                    public void onComplete(int Response)
                    {
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
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
                        });
                    }

                    @Override
                    public void onError(final String err)
                    {
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Snackbar.make(view.getRootView(),"Can't connect. try again.",Snackbar.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                SD.Send();
            }
        }).start();
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

    public void ChangeIPPressed(final View view)
    {
        final View DialogView = LayoutInflater.from(this).inflate(R.layout.new_ip_dialog_layout,(ViewGroup) view.getParent(),false);
        final AlertDialog Dialog = new AlertDialog.Builder(this)
                .setView(DialogView)
                .create();

        final EditText IP_ET = DialogView.findViewById(R.id.IPET);

        DialogView.findViewById(R.id.CloseButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Dialog.dismiss();
            }
        });

        DialogView.findViewById(R.id.IpUpdateButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String NewIP = IP_ET.getText().toString();
                if(NewIP.isEmpty())
                {
                    IP_ET.setError("Empty!");
                    IP_ET.requestFocus();
                }
                else
                {
                    Dialog.dismiss();
                    IP = NewIP;
                    getSharedPreferences(getApplicationContext().getPackageName(),MODE_PRIVATE).edit().putString("IP",NewIP).apply();
                    Toast.makeText(EntryActivity.this,"IP Updated!",Snackbar.LENGTH_LONG).show();
                    NewIP = "Current IP " + NewIP;
                    CurrIP.setText(NewIP);
                }
            }
        });

        Dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        this.finish();
    }
}
