package com.android.meelz.meelz;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PrimaryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PrimaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrimaryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ArrayAdapter<String> mRestaurantsAdapter;

    public PrimaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrimaryFragment newInstance(String param1, String param2) {
        PrimaryFragment fragment = new PrimaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recList = (RecyclerView) rootView.findViewById(R.id.recycleView_restaurants);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.background_prato_destaque);



        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        recList.setNestedScrollingEnabled(false);
        PrimaryAdapter adapter = new PrimaryAdapter(createList(5));
        recList.setAdapter(adapter);

        return rootView;

    }

    public List<CardInfo> createList(int size){

        List<CardInfo> result = new ArrayList<CardInfo>();
        String prato = "Pizza Mexicana ";
        String local = "Mimmos ";
        String distancia = "150 m";
        String preco = "450 MTN ";
        String info = "Localizado na entrada do campus principal da Universidade Eduardo Mondlane ";



        for (int i=1; i <= size; i++) {
            CardInfo ci = new CardInfo();
            ci.nomePrato = prato;
            ci.nomeLocal = local;
            ci.distancia = distancia;
            ci.preco = preco;
            ci.info = info;

            result.add(ci);

        }

        return result;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class PrimaryAdapter extends RecyclerView.Adapter<PrimaryViewHolder> {

        private List<CardInfo> restarantList;

        public PrimaryAdapter(List<CardInfo> restarantList) {
            this.restarantList = restarantList;
        }

        @Override
        public PrimaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.list_item_card, parent, false);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), LocalActivity.class);
                    v.getContext().startActivity(intent);
                }
            });

            return new PrimaryViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(PrimaryViewHolder holder, int position) {
            CardInfo ci = restarantList.get(position);
            PrimaryViewHolder.vNomePrato.setText(ci.nomePrato);
            PrimaryViewHolder.vDistancia.setText(ci.distancia);
            PrimaryViewHolder.vNomeLocal.setText(ci.nomeLocal);
            PrimaryViewHolder.vPreco.setText(ci.preco);


        }

        @Override
        public int getItemCount() {
            return restarantList.size();
        }

    }

    public static class CardInfo {

        protected String nomePrato;
        protected String nomeLocal;
        protected String distancia;
        protected String info;
        protected String preco;

    }


    public static class PrimaryViewHolder extends RecyclerView.ViewHolder {
        protected static TextView vNomePrato;
        protected static TextView vNomeLocal;
        protected static TextView vDistancia;
        protected static TextView vPreco;


        public PrimaryViewHolder(View v) {
            super(v);
            vNomePrato =  (TextView) v.findViewById(R.id.txt_nome_prato);
            vPreco =  (TextView) v.findViewById(R.id.txt_preco);
            vNomeLocal = (TextView)  v.findViewById(R.id.txt_nome_local);
            vDistancia = (TextView)  v.findViewById(R.id.txt_distancia);
        }
    }


}




