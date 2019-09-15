//package com.example.TodayActivity;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ListView;
//
//import java.util.ArrayList;
//
//public class like extends Fragment {
//
//    private ListView listView;
//    private like_adapter like_adapter;
//    private ArrayList<like_people> list = new ArrayList<>();
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = (View)inflater.inflate(R.layout.like,container,false);
//        return view;
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        listView = (ListView)getActivity().findViewById(R.id.like_list);
//        like_adapter = new like_adapter(getActivity(),list);
//        listView.setAdapter(like_adapter);
//        initData();
//    }
//
//    public void initData(){
//        list.add(new like_people(R.mipmap.ic_launcher_round,"asfa","asfasfasfr"));
//        list.add(new like_people(R.mipmap.ic_launcher_round,"ashfkjashf","asfasfasfr"));
//        list.add(new like_people(R.mipmap.ic_launcher_round,"asfasfasf","asfasfasfr"));
//        list.add(new like_people(R.mipmap.ic_launcher_round,"asfagasgas","asfasfasfr"));
//        list.add(new like_people(R.mipmap.ic_launcher_round,"asfasgfasg","asfasfasfr"));
//    }
//}
