package layout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.david.stockwishlist.R;
import com.google.gson.Gson;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import dao.StockQuoteDAO;
import entities.StockQuoteEntity;
import roboguice.RoboGuice;

import static android.view.View.*;

public class FragmentFetchDatabase extends BaseFragment implements OnClickListener {

    private TextView tvData;
    private CheckBox cbStocks, cbMutualFunds;
    private Button bFetchData, bDeleteAll;
    private Boolean isStockChecked, isMFChecked;
    @Inject
    private StockQuoteDAO stockQuoteDAO;

    public static BaseFragment newInstance() {
        Bundle bundle = new Bundle();
        BaseFragment fragment = new FragmentFetchDatabase();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.setHasOptionsMenu(true);
        RoboGuice.injectMembers(getActivity().getApplicationContext(), this);

    }


    @Override
    int getFragmentLayoutResourceId() {
        return R.layout.fragment_fetch_database;
    }

    @Override
    void findViews() {
        tvData = (TextView) mRootView.findViewById(R.id.tvData);
        bFetchData = (Button) mRootView.findViewById(R.id.bGetData);
        bDeleteAll = (Button) mRootView.findViewById(R.id.bDelete);
        cbMutualFunds = (CheckBox) mRootView.findViewById(R.id.cbMutualFunds);
        cbStocks = (CheckBox) mRootView.findViewById(R.id.cbStocks);
        bFetchData.setOnClickListener(this);
        bDeleteAll.setOnClickListener(this);

        cbStocks.setChecked(true);
        cbMutualFunds.setChecked(true);
        isMFChecked = true;
        isStockChecked = true;

        cbStocks.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isStockChecked = b;
                Log.d("stock checked ", isStockChecked + "");
            }
        });
        cbMutualFunds.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isMFChecked = b;
                Log.d("mf checked ", isMFChecked + "");
            }
        });
    }

    private void getStockDataFromLocalDB() {
        String data = "";
        try {
            List<StockQuoteEntity> listOfEntites = new ArrayList<>();
            if (isMFChecked) {
                listOfEntites = stockQuoteDAO.getListStockEntitiesByType("mutualFund");
            }
            if (isStockChecked) {
                listOfEntites = stockQuoteDAO.getListStockEntitiesByType("stock");
            }
            if (isMFChecked && isStockChecked) {
                listOfEntites = stockQuoteDAO.getListStockEntitiesByType("stock");
                listOfEntites.addAll(stockQuoteDAO.getListStockEntitiesByType("mutualFund"));
            }
            Log.d("list of ent= ", " " + listOfEntites.size());
            for (int i = 0; i < listOfEntites.size(); i++) {
                data = data + "\n" + listOfEntites.get(i).getName() + "\n" + new Gson().toJson(listOfEntites.get(i), StockQuoteEntity.class) + "\n";
            }
            tvData.setText("Number of investments found in DB= " + listOfEntites.size() + "\n" + data);
            Log.d("db   ", data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == bFetchData) {
            getStockDataFromLocalDB();
        }
        if (view == bDeleteAll) {
            deleteAllDataInDB();
        }
    }

    private void deleteAllDataInDB() {
        Log.d("deleting", "deleting");
        List<StockQuoteEntity> listOfEntites = stockQuoteDAO.getListStockEntitiesByType("stock");
        listOfEntites.addAll(stockQuoteDAO.getListStockEntitiesByType("mutualFund"));

        Log.d("deleting", listOfEntites.size() + "");
        for (StockQuoteEntity a : listOfEntites) {
            stockQuoteDAO.delete(a);
        }
        tvData.setText("");
    }
}