package com.example.apk_kpa.app;

        import android.app.Fragment;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

public class Statistika extends Fragment {
    private static String myPoints;
    private static String AQuestions;

    public Statistika(){}

    public Statistika(String myPoints,String AQuestions){
        this.myPoints = myPoints;
        this.AQuestions = AQuestions;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.statistika, container, false);

        // Sukaupti taskai
        TextView a = (TextView) rootView.findViewById(R.id.taskaiLbl);
        a.setText(myPoints);

        // Atsakyta klausimu
        TextView n = (TextView) rootView.findViewById(R.id.atsakytaLbl);
        n.setText(AQuestions);

        return rootView;
    }
}
