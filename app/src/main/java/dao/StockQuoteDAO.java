package dao;

import com.google.inject.Singleton;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

import database.DatabaseAccesor;
import entities.StockQuoteEntity;

@Singleton
@DatabaseAccesor.CreateOrmLiteTable(StockQuoteEntity.class)
public class StockQuoteDAO extends DatabaseAccesor {
    private String CANNOT_GET = "Can't get ";


    public void createOrUpdate(StockQuoteEntity object) {
        if (object == null) {
            return;
        }
        try {
            getDatabase().getDao(StockQuoteEntity.class).createOrUpdate(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(StockQuoteEntity object) {
        if (object == null) {
            return;
        }
        try {
            getDatabase().getDao(StockQuoteEntity.class).delete(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public StockQuoteEntity getStockEntityBySymbol(String symbol) {
        if (symbol == null || symbol.isEmpty())
            return null;
        try {
            QueryBuilder<StockQuoteEntity, Integer> qb = getDatabase().getDao(StockQuoteEntity.class).queryBuilder();
            Where<StockQuoteEntity, Integer> where = qb.where();
            where.eq("symbol", symbol);
            PreparedQuery<StockQuoteEntity> pq = qb.prepare();
            List<StockQuoteEntity> objects = getDatabase().getDao(StockQuoteEntity.class).query(pq);
            if (objects != null && !objects.isEmpty())
                return objects.get(0);
            return null;
        } catch (SQLException e) {
            throw new IllegalArgumentException(CANNOT_GET + StockQuoteEntity.class.getSimpleName() + " for symbol " + symbol, e);
        }
    }

    public List<StockQuoteEntity> getListStockEntitiesByType(String type) {
        try {
            QueryBuilder<StockQuoteEntity, Integer> qb = getDatabase().getDao(StockQuoteEntity.class).queryBuilder();
            List<StockQuoteEntity> entities = qb.where().eq("type", type).query();
            return entities;
        } catch (SQLException e) {
            throw new IllegalArgumentException(CANNOT_GET + StockQuoteEntity.class.getSimpleName() + " for symbol ", e);
        }
    }


}