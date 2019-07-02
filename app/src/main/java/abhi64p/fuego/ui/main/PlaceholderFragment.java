package abhi64p.fuego.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import abhi64p.fuego.R;

public class PlaceholderFragment extends Fragment
{

    private static final String ARG_SECTION_NUMBER = "section_number";

     static PlaceholderFragment newInstance(int index)
    {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        PageViewModel pageViewModel;
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null)
        {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View root =  inflater.inflate(R.layout.fragment_burner_nob, container, false);
        CardView OffCV = root.findViewById(R.id.OffCV);
        CardView OnCV = root.findViewById(R.id.OnCV);
        CardView SimCV = root.findViewById(R.id.SimCV);
        final View CenterNob = root.findViewById(R.id.NobIV);

        final FragmentActivity fragmentActivity = getActivity();
        if(fragmentActivity != null)
        {

            OffCV.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    CenterNob.animate().rotation(-45).setDuration(200).start();

                }
            });
            OnCV.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    CenterNob.animate().rotation(0).setDuration(200).start();

                }
            });
            SimCV.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    CenterNob.animate().rotation(45).setDuration(200).start();
                }
            });
        }
        return root;
    }
}