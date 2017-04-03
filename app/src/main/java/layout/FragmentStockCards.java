package layout;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.david.stockwishlist.Activity;
import com.example.david.stockwishlist.MyAdapter;
import com.example.david.stockwishlist.R;

import dao.StockQuoteDAO;
import entities.StockQuoteEntity;

/**
 * Created by David on 3/7/17.
 */

public class FragmentStockCards extends BaseFragment implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: 3/31/17 this is actually done within method getFragmentLayoutResourceId 
        setContentView(R.layout.recycler_view_stock_cards);

        // TODO: 3/31/17 needs to go to method findViews 
        mRecyclerView = (RecyclerView) findViewById(R.id.rc_stock_cards);
        

        // changes in content will not change size of recycler view, so set fixed size
        mRecyclerView.setHasFixedSize(true);

        // set layout manager
        mRecyclerView.setLayoutManager(mLayoutManager);

        // initialize and then set adapter
            // expecting an input of List<StockQuoteEntity>
        // the adapter should connect to the DAO

        // TODO: 3/29/17 look at MyAdapter constructor to see what dataset info to put here to work out.
        // DAO object needs to go within 'new MyAdapter(__)'. How do I get a handle to the DAO?
        // The dao is instantiated over in FragmentStocks but I could probably instantiate one here too...
        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    int getFragmentLayoutResourceId() {
        return 0;
    }

    @Override
    void findViews() {

    }
}