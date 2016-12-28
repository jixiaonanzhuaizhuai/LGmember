package com.lgmember.lgmember;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lgmember.adapter.ActivityAdapter;
import com.lgmember.model.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentSoonJoin extends Fragment {

	private List<Fruit> fruitList = new ArrayList<Fruit>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_soonjoin, container, false);

		initFruits();
		ListView listview1 = (ListView)view.findViewById(R.id.listview1);
		FruitAdapter fruitAdapter = new FruitAdapter(getActivity(), R.layout.activity_activitylistitem, fruitList);
		listview1.setAdapter(fruitAdapter);
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent();
				intent.putExtra("position",String.valueOf(position));
				intent.setClass(getActivity(), ActivityDetailActivity.class);
				startActivity(intent);
			}
		});
		return view;
	}

	private void initFruits() {
		Fruit apple = new Fruit("Apple", R.mipmap.image2, "治下痢、降血脂、滋润皮肤。");
		fruitList.add(apple);
		Fruit banana = new Fruit("banana", R.mipmap.image7, "香蕉被称为百果之冠,具有润肠通便,清热解毒,健脑益智,通血脉,填精髓,降血压等功效。");
		fruitList.add(banana);
		Fruit cherry = new Fruit("cherry", R.mipmap.image3, "樱桃补中益气，祛风胜湿");
		fruitList.add(cherry);
		Fruit grape = new Fruit("grape", R.mipmap.image1, "葡萄营养丰富、酸甜可口，因此成为世界四大水果(苹果、葡萄、柑橘和香蕉)之一");
		fruitList.add(grape);
		Fruit kiwi = new Fruit("kiwi", R.mipmap.image5, "能促使血液循环顺畅，增进性能力。");
		fruitList.add(kiwi);
		Fruit lemon = new Fruit("lemon", R.mipmap.image6, "可增强消化、出汗过多、食欲不振、体力倦怠、减肥解酒。");
		fruitList.add(lemon);
		Fruit mango = new Fruit("mango", R.mipmap.image7, "芒果果实营养价值极高，维生素A的含量比杏子还要多出1倍 ");
		fruitList.add(mango);
		Fruit orange = new Fruit("orange", R.mipmap.image1, "桔子含水量高、营养丰富");
		fruitList.add(orange);
		Fruit pear = new Fruit("pear", R.mipmap.image2, "清解热毒、镇咳化痰。");
		fruitList.add(pear);
		Fruit persimmon = new Fruit("persimmon", R.mipmap.image1, "柿子营养丰富，但要吃得健康，不过要注意一些与柿子相克的食物 ");
		fruitList.add(persimmon);
		Fruit pineapple = new Fruit("pineapple", R.mipmap.image1, "菠萝清香宜人，汁多味甜，广受人们喜爱。");
		fruitList.add(pineapple);
		Fruit strawberry = new Fruit("strawberry", R.mipmap.image1,
				"去火、解暑、清热；促进胃肠蠕动、帮助消化、改善便秘；预防痔疮、肠癌 ");
		fruitList.add(strawberry);
		Fruit watermelon = new Fruit("watermelon", R.mipmap.image1, "西瓜味道甘味多汁，清爽解渴，是盛夏佳果。");
		fruitList.add(watermelon);
	}
	public class FruitAdapter extends ArrayAdapter {

		private int resourceId;

		public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects) {
			super(context, textViewResourceId, objects);
			resourceId = textViewResourceId;
		}


		public View getView(int position, View convertView, ViewGroup parent) {
			Fruit fruit = (Fruit) getItem(position);
			View view;
			if (convertView == null) {
				view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			} else {
				view = convertView;
			}
			ImageView fruitImage = (ImageView) view.findViewById(R.id.activity_img);
			TextView fruitName = (TextView) view.findViewById(R.id.activity_name);
			TextView fruitIntroduction = (TextView) view.findViewById(R.id.activity_introduction);

			fruitImage.setImageResource(fruit.getImageId());
			fruitName.setText(fruit.getName());
			fruitIntroduction.setText(fruit.getIntroduction());
			return view;

		}
	}
	public class Fruit {
		private String name;
		private int imageId;
		private String introduction;

		public Fruit(String name, int imageId, String introduction) {
			this.name = name;
			this.introduction = introduction;
			this.imageId = imageId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getIntroduction() {
			return introduction;
		}
		public void setIntroduction(String introduction) {
			this.introduction = introduction;
		}
		public int getImageId() {
			return imageId;
		}
		public void setImageId(int imageId) {
			this.imageId = imageId;
		}
	}
}

