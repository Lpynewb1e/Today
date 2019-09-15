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
//public class commit extends Fragment {
//    private ListView listView;
//    private commit_adapter commit_adapter;
//    private ArrayList<commit_people> list = new ArrayList<>();
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = (View)inflater.inflate(R.layout.commit,container,false);
//        return view;
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        listView = (ListView)getActivity().findViewById(R.id.commit_list);
//        commit_adapter = new commit_adapter(getActivity(),list);
//        listView.setAdapter(commit_adapter);
//        initData();
//    }
//
//    public void initData(){
//        list.add(new commit_people(R.mipmap.ic_launcher,"a","asf",R.mipmap.commit_like,"0","hi,nice to meet you","15:11","回复"));
//        list.add(new commit_people(R.mipmap.ic_launcher,"b","asf",R.mipmap.commit_like,"0","hi,nice to meet you","15:11","回复"));
//        list.add(new commit_people(R.mipmap.ic_launcher,"c","asf",R.mipmap.commit_like,"0","hi,nice to meet you","15:11","回复"));
//        list.add(new commit_people(R.mipmap.ic_launcher,"d","asf",R.mipmap.commit_like,"0","hi,nice to meet you","15:11","回复"));
//        list.add(new commit_people(R.mipmap.ic_launcher,"e","asf",R.mipmap.commit_like,"0","hi,nice to meet you","15:11","回复"));
//        list.add(new commit_people(R.mipmap.ic_launcher,"f","asf",R.mipmap.commit_like,"0","hi,nice to meet you","15:11","回复"));
//        list.add(new commit_people(R.mipmap.ic_launcher,"g","asf",R.mipmap.commit_like,"0","hi,nice to meet you","15:11","回复"));
//        list.add(new commit_people(R.mipmap.ic_launcher,"h","asf",R.mipmap.commit_like,"0","hi,nice to meet you","15:11","回复"));
//        list.add(new commit_people(R.mipmap.ic_launcher,"i","asf",R.mipmap.commit_like,"0","hi,nice to meet you","15:11","回复"));
//        list.add(new commit_people(R.mipmap.ic_launcher,"j","asf",R.mipmap.commit_like,"0","hi,nice to meet you","15:11","回复"));
//        list.add(new commit_people(R.mipmap.ic_launcher,"k","asf",R.mipmap.commit_like,"0","hi,nice to meet you","15:11","回复"));
//        list.add(new commit_people(R.mipmap.ic_launcher,"l","asf",R.mipmap.commit_like,"0","hi,nice to meet you","15:11","回复"));
//        list.add(new commit_people(R.mipmap.ic_launcher,"m","asf",R.mipmap.commit_like,"0","hi,nice to meet you","15:11","回复"));
//        list.add(new commit_people(R.mipmap.ic_launcher,"n","asf",R.mipmap.commit_like,"0","hi,nice to meet you","15:11","回复"));
//        list.add(new commit_people(R.mipmap.ic_launcher,"o","asf",R.mipmap.commit_like,"0","hi,nice to meet you","15:11","回复"));
//        list.add(new commit_people(R.mipmap.ic_launcher,"p","asf",R.mipmap.commit_like,"0","hi,nice to meet you","15:11","回复"));
//        list.add(new commit_people(R.mipmap.ic_launcher,"q","asf",R.mipmap.commit_like,"0","hi,nice to meet you","15:11","回复"));
//    }
//}
