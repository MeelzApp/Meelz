package com.android.meelz.meelz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class LocalActivity extends AppCompatActivity {

    CollapsingToolbarLayout collapseToolbar = null;
    ImageView toolbarBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        RecyclerView recList = (RecyclerView) findViewById(R.id.recycleView_pratos);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        recList.setNestedScrollingEnabled(false);
        PratosAdapter adapter = new PratosAdapter(createList(5));
        recList.setAdapter(adapter);

        collapseToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarBackground = (ImageView) findViewById(R.id.toolbar_background);

        toolbarBackground.setImageResource(R.drawable.background_material_red);

        dynamicToolbarColor();
        toolbarTextApperance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void dynamicToolbarColor(){

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                collapseToolbar.setContentScrimColor(palette.getMutedColor(getResources().getColor(R.color.colorPrimary)));
                collapseToolbar.setStatusBarScrimColor(palette.getMutedColor(getResources().getColor(R.color.colorPrimaryDark)));
            }
        });

    }

    private void toolbarTextApperance() {
        collapseToolbar.setCollapsedTitleTextAppearance(R.style.collapsedappbar);
        collapseToolbar.setExpandedTitleTextAppearance(R.style.expandedappbar);
    }





    public List<PratosInfo> createList(int size){

        List<PratosInfo> result = new ArrayList<PratosInfo>();
        String prato = "Tosta mista ";
        String preco = "450";
        String descricao = "Tosta feita com algns ingredientes ya dshfsaajdgsahs asyudgasdbas asdgasdgas asudytas";



        for (int i=1; i <= size; i++) {
            PratosInfo pi = new PratosInfo();
            pi.nomePrato = prato;
            pi.descricao = descricao;
            pi.preco = preco;

            result.add(pi);

        }

        return result;
    }


    public class PratosAdapter extends RecyclerView.Adapter<PratosViewHolder> {

        private List<PratosInfo> listaPratos;

        public PratosAdapter(List<PratosInfo> restarantList) {
            this.listaPratos = restarantList;
        }

        @Override
        public PratosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.
                    from(parent.getContext()).
                    inflate(R.layout.list_item_card_pratos, parent, false);

            return new PratosViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(PratosViewHolder holder, int position) {
            PratosInfo ci = listaPratos.get(position);
            PratosViewHolder.vNomePrato.setText(ci.nomePrato);
            PratosViewHolder.vDescricao.setText(ci.descricao);
            PratosViewHolder.vPreco.setText(ci.preco);
        }



        @Override
        public int getItemCount() {
            return listaPratos.size();
        }

    }

    public static class PratosInfo {

        protected String nomePrato;
        protected String descricao;
        protected String preco;

    }

    public static class PratosViewHolder extends RecyclerView.ViewHolder {
        protected static TextView vNomePrato;
        protected static TextView vDescricao;
        protected static TextView vPreco;


        public PratosViewHolder(View v) {
            super(v);
            vNomePrato =  (TextView) v.findViewById(R.id.txt_nome_prato);
            vPreco =  (TextView) v.findViewById(R.id.txt_preco);
            vDescricao = (TextView)  v.findViewById(R.id.txt_descricao);
        }
    }


}
