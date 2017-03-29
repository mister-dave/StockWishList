package layout;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.david.stockwishlist.Activity;
import com.example.david.stockwishlist.MyAdapter;
import com.example.david.stockwishlist.R;

import dao.StockQuoteDAO;
import entities.StockQuoteEntity;

/**
 * Created by David on 3/7/17.
 */

public class FragmentStockCards extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_stock_cards);
        mRecyclerView = (RecyclerView) findViewById(R.id.rc_stock_cards);

        // changes in content will not change size of recycler view, so set fixed size
        mRecyclerView.setHasFixedSize(true);

        // set layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);

        // initialize and then set adapter
        // the adapter should connect to the DAO
        // // TODO: 3/29/17 look at MyAdapter constructor to see what dataset info to put here to work out. Ultimately needs to connect
        // to the DAO somehow.
        // expecting an input of String []
        mAdapter = new MyAdapter( );
        mRecyclerView.setAdapter(mAdapter);
    }
}