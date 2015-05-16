package com.cxt.esl.util.db;

import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.cxt.esl.config.domain.Config;
import com.cxt.esl.good.domain.Good;
import com.cxt.esl.good.domain.GoodUpdateHistory;
import com.cxt.esl.kind.domain.Kind;
import com.cxt.esl.label.domain.Label;
import com.cxt.esl.model.domain.Model;
import com.cxt.esl.pattern.domain.Pattern;
import com.cxt.esl.sale.domain.Order;
import com.cxt.esl.user.domain.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class ESLDatebaseHelper extends OrmLiteSqliteOpenHelper{

	// name of the database file for your application -- change to something
	// appropriate for your app
	private static final String DATABASE_NAME = "esl.db";
	// any time you make changes to your database objects, you may have to
	// increase the database version
	private static final int DATABASE_VERSION = 1;

	// the DAO object we use to access the SimpleData table
	private Dao<Label, Integer> labelDao = null;
	private Dao<Pattern, Integer> patternDao = null;
	private Dao<Model, Integer> modelDao = null;
	private Dao<Good, Integer> goodDao = null;
	private Dao<GoodUpdateHistory, Integer> goodUpdateHistoryDao = null;
	private Dao<Kind, Integer> kindDao = null;
	private Dao<User, Integer> userDao = null;
	private Dao<Config, Integer> configDao = null;
	private Dao<Order, Integer> orderDao = null;
	private static final AtomicInteger usageCounter = new AtomicInteger(0);

	// we do this so there is only one helper
	private static ESLDatebaseHelper helper = null;

	private ESLDatebaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	
	/**
	 * Get the helper, possibly constructing it if necessary. For each call to
	 * this method, there should be 1 and only 1 call to {@link #close()}.
	 */
	public static synchronized ESLDatebaseHelper getHelper(Context context) {
		if (helper == null) {
			helper = new ESLDatebaseHelper(context);
		}
		usageCounter.incrementAndGet();
		return helper;
	}

	/**
	 * This is called when the database is first created. Usually you should
	 * call createTable statements here to create the tables that will store
	 * your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Label.class);
			TableUtils.createTable(connectionSource, Pattern.class);
			TableUtils.createTable(connectionSource, Model.class);
			TableUtils.createTable(connectionSource, Good.class);
			TableUtils.createTable(connectionSource, GoodUpdateHistory.class);
			TableUtils.createTable(connectionSource, Kind.class);
			TableUtils.createTable(connectionSource, User.class);
			TableUtils.createTable(connectionSource, Config.class);
			TableUtils.createTable(connectionSource, Order.class);
			
			User u = new User();
			u.setCreateBy(0);
			u.setCreateDate(new Date());
			u.setLastUpdateBy(0);
			u.setLastUpdateDate(new Date());
			u.setPassword("123456");
			u.setRoleName(0);
			u.setStatus(0);
			u.setUserCode("admin");
			u.setUserName("系统管理员");
			userDao.create(u);
			
			labelDao = getLabelDao();
			Label label = new Label();
			label.setEslId(1);
			label.setGoodsId(13123);
			label.setPatternId(12);
			label.setWorkStatus(1);
			label.setMacId("123123");
			label.setModelId(234);
			labelDao.create(label);
			Label label2 = new Label();
			label2.setEslId(123);
			labelDao.create(label2);
			
			patternDao = getPatternDao();
			Pattern p1  = new Pattern(); 
			p1.setCode("test");
			p1.setOrderNum(1);
			p1.setPatternName("2.1寸白板");
			p1.setModel("ble2.1w");
			p1.setReadme("ble2.1w");
			patternDao.create(p1);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * This is called when your application is upgraded and it has a higher
	 * version number. This allows you to adjust the various data to match the
	 * new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, Label.class, true);
			TableUtils.dropTable(connectionSource, Pattern.class, true);
			TableUtils.dropTable(connectionSource, Model.class, true);
			TableUtils.dropTable(connectionSource, Good.class, true);
			TableUtils.dropTable(connectionSource, GoodUpdateHistory.class, true);
			TableUtils.dropTable(connectionSource, Kind.class, true);
			TableUtils.dropTable(connectionSource, User.class, true);
			TableUtils.dropTable(connectionSource, Config.class, true);
			TableUtils.dropTable(connectionSource, Order.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the Database Access Object (DAO) for our SimpleData class. It
	 * will create it or just give the cached value.
	 */
	public Dao<Label, Integer> getLabelDao() throws SQLException {
		if (labelDao == null) {
			labelDao = getDao(Label.class);
		}
		return labelDao;
	}
	public Dao<Pattern, Integer> getPatternDao() throws SQLException {
		if (patternDao == null) {
			patternDao = getDao(Pattern.class);
		}
		return patternDao;
	}
	public Dao<Model, Integer> getModelDao() throws SQLException {
		if (modelDao == null) {
			modelDao = getDao(Model.class);
		}
		return modelDao;
	}
	public Dao<Good, Integer> getGoodDao() throws SQLException {
		if (goodDao == null) {
			goodDao = getDao(Good.class);
		}
		return goodDao;
	}
	public Dao<GoodUpdateHistory, Integer> getGoodUpdateHistoryDao() throws SQLException {
		if (goodUpdateHistoryDao == null) {
			goodUpdateHistoryDao = getDao(GoodUpdateHistory.class);
		}
		return goodUpdateHistoryDao;
	}
	public Dao<Kind, Integer> getKindDao() throws SQLException {
		if (kindDao == null) {
			kindDao = getDao(Kind.class);
		}
		return kindDao;
	}
	public Dao<User, Integer> getUserDao() throws SQLException {
		if (userDao == null) {
			userDao = getDao(User.class);
		}
		return userDao;
	}
	public Dao<Config, Integer> getConfigDao() throws SQLException {
		if (configDao == null) {
			configDao = getDao(Config.class);
		}
		return configDao;
	}
	public Dao<Order, Integer> getOrderDao() throws SQLException {
		if (orderDao == null) {
			orderDao = getDao(Order.class);
		}
		return orderDao;
	}

	/**
	 * Close the database connections and clear any cached DAOs. For each call
	 * to {@link #getHelper(Context)}, there should be 1 and only 1 call to this
	 * method. If there were 3 calls to {@link #getHelper(Context)} then on the
	 * 3rd call to this method, the helper and the underlying database
	 * connections will be closed.
	 */
	@Override
	public void close() {
		if (usageCounter.decrementAndGet() == 0) {
			super.close();
			labelDao = null;
			patternDao = null;
			modelDao = null;
			goodDao = null;
			goodUpdateHistoryDao = null;
			kindDao = null;
			userDao = null;
			configDao = null;
			orderDao = null;
			helper = null;
		}
	}
}
