package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.stockwishlist.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import dao.StockQuoteDAO;
import entities.StockQuoteEntity;
import facades.StockFacade;
import roboguice.RoboGuice;
import utils.Callback;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link FragmentStocks#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentStocks extends BaseFragment implements View.OnClickListener {

    private TextView tvStockQuoteData;
    @Inject
    private StockQuoteDAO dao;

    private EditText etStockSymbol;
    private Button bEnter, bClear;
    private String LOG_TAG = "fragmentStocks";
    private FloatingActionMenu fabMenu;
    private FloatingActionButton fabItem1, fabItem2, fabItem3;
    private RelativeLayout rlBackground;


    private List<FloatingActionMenu> menus = new ArrayList<>();


    public static BaseFragment newInstance() {
        Bundle bundle = new Bundle();
        BaseFragment fragment = new FragmentStocks();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.setHasOptionsMenu(true);
        RoboGuice.injectMembers(getActivity().getApplicationContext(), this);
    }

    //
    @Override
    int getFragmentLayoutResourceId() {
        return R.layout.fragment_stocks;
    }

    @Override
    void findViews() {
        fabMenu = (FloatingActionMenu) mRootView.findViewById(R.id.menu_yellow);
        fabItem1 = (FloatingActionButton) mRootView.findViewById(R.id.fabMenuItem1);
        fabItem2 = (FloatingActionButton) mRootView.findViewById(R.id.fabMenuItem2);
        fabItem3 = (FloatingActionButton) mRootView.findViewById(R.id.fabMenuItem3);
        rlBackground = (RelativeLayout) mRootView.findViewById(R.id.rlFragmentBackground);

        tvStockQuoteData = (TextView) mRootView.findViewById(R.id.tvStockQuote);
        etStockSymbol = (EditText) mRootView.findViewById(R.id.etStockSymbol);
        bEnter = (Button) mRootView.findViewById(R.id.bEnter);
        bClear = (Button) mRootView.findViewById(R.id.bClear);

        rlBackground.setOnClickListener(clickListener);
        fabItem1.setOnClickListener(clickListener);
        fabItem2.setOnClickListener(clickListener);
        fabItem3.setOnClickListener(clickListener);

        bEnter.setOnClickListener(this);
        bClear.setOnClickListener(this);

        fabItem1.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ic_menu_slideshow));
        menus.add(fabMenu);

    }

    /**
     * this is for the fab and animations
     */
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fabMenuItem1:
                    Toast.makeText(getContext(), "1", Toast.LENGTH_LONG).show();
                    fabMenu.close(true);
                    break;
                case R.id.fabMenuItem2:
                    Toast.makeText(getContext(), "2", Toast.LENGTH_LONG).show();
                    fabMenu.close(true);
                    break;
                case R.id.fabMenuItem3:
                    Toast.makeText(getContext(), "3", Toast.LENGTH_LONG).show();
                    fabMenu.close(true);
                    break;
                case R.id.rlFragmentBackground:
                    fabMenu.close(true);
                    break;

            }
        }
    };


    private void runWebRequestAndLoadIntoDB(final String symbol) {
        StockFacade stockFacade = new StockFacade();
        stockFacade.getSingleStock(symbol, new Callback<StockQuoteEntity>() {
            @Override
            public void onCallback(StockQuoteEntity data, Exception exception) {
                if (data != null && isAlive()) {
                    String lastCharOfSymbol = symbol.charAt(symbol.length() - 1) + "";
                    if (symbol.length() == 5 && lastCharOfSymbol.equalsIgnoreCase("x")) {
                        data.setType("mutualFund");
                    } else {
                        data.setType("stock");
                    }
                    Log.d(LOG_TAG, data.getType());
                    String name = data.getName();
                    String symbol = data.getSymbol();
                    String change = data.getChange();
                    Log.d(LOG_TAG, name + symbol + change);
                    tvStockQuoteData.setText("Company: " + data.getName() + "\nLast Trade Price: $" + data.getLastTradePriceOnly()+"\nDay Change: " + data.getChange());
                    if (data.getName() == null && data.getLastTradePriceOnly() == null) {
                        //dont put in database
                    } else {
                        //put in database
                        dao.createOrUpdate(data);
                        Toast.makeText(getContext(), "Done. Added " + name + " to your portfolio.", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getContext(), "Web error. Please Try again.", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void clearEditField() {
        etStockSymbol.setText("");
        tvStockQuoteData.setText("");
        Toast.makeText(getContext(), "Cleared", Toast.LENGTH_LONG).show();


    }


    @Override
    public void onClick(View view) {
        if (view == bClear) {
            clearEditField();
        } else if (view == bEnter) {
            String symbol = etStockSymbol.getText().toString();
            runWebRequestAndLoadIntoDB(symbol);
        }
    }
}