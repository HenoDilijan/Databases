package club.aborigen.database.ui.helper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import club.aborigen.database.databinding.FragmentHelperBinding;


public class HelperFragment extends Fragment {

    private FragmentHelperBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHelperBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHelper;
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}