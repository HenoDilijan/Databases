package club.aborigen.database.ui.room;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import club.aborigen.database.databinding.FragmentRoomBinding;

public class RoomFragment extends Fragment {

    private FragmentRoomBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRoomBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textRoom;

        Reader r = new Reader();
        r.start();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    class Reader extends Thread {
        @Override
        public void run() {
            super.run();

            EmployeeEntity emp1 = new EmployeeEntity();
            emp1.name = "Ashot Misakyan";
            emp1.salary = 450000;
            DatabaseClient.getInstance(getContext()).getAppDatabase().employeeDao().insertEmployee(emp1);

            EmployeeEntity emp2 = new EmployeeEntity();
            emp2.name = "Slavik Ushunc";
            emp2.salary = 250000;
            DatabaseClient.getInstance(getContext()).getAppDatabase().employeeDao().insertEmployee(emp2);

            List<EmployeeEntity> employees = DatabaseClient.getInstance(getContext()).getAppDatabase().employeeDao().getAll();
            employees.forEach(employee -> {
                Log.i("UWC", "Room Employee: " + employee.name);
            });
        }
    }
}