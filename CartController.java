package jp.co.internous.altair.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.altair.model.domain.TblCart;
import jp.co.internous.altair.model.domain.dto.CartDto;
import jp.co.internous.altair.model.form.CartForm;
import jp.co.internous.altair.model.mapper.TblCartMapper;
import jp.co.internous.altair.model.session.LoginSession;

@Controller
@RequestMapping("/altair/cart")
public class CartController {
	
	@Autowired
	private TblCartMapper cartMapper;
	
	@Autowired
	private LoginSession loginSession;
	
	private Gson gson = new Gson();
	
	// カート画面表示
	@RequestMapping("/")
	public String index(Model m) {
	    // loginSessionからユーザーの情報を取得
		// ログインの状態によって変数userIdを代入
		int userId = loginSession.getLogined() ? loginSession.getUserId() : loginSession.getTmpUserId();
		
	    // userIdに紐づくカート情報を取得し表示	
		List<CartDto> carts = cartMapper.findByUserId(userId);
		m.addAttribute("loginSession",loginSession);
		m.addAttribute("carts",carts);
		
	    //「cart.html」表示
		return "cart";
	}
	
	// カート追加処理
	@RequestMapping("/add")
	public String addCart(CartForm f, Model m) {
		// loginSessionからユーザーの情報を取得
		// ログインの状態によって変数userIdを代入
		int userId = loginSession.getLogined() ? loginSession.getUserId() : loginSession.getTmpUserId();
		f.setUserId(userId);
		
		// CartFormからの情報をTblCartに置くためインスタンス化
		TblCart cart = new TblCart(f);
		// カートテーブルに挿入/更新
		// すでに同じ商品がカート内にある場合は更新、ない場合は追加
		int result = 0;
		if (cartMapper.findCountByUserIdAndProuductId(userId, f.getProductId()) > 0) {
			result = cartMapper.update(cart);
		} else {
			result = cartMapper.insert(cart);
		}
		if(result > 0) {
			// userIdに紐づくカート情報を取得し表示
			List<CartDto> carts = cartMapper.findByUserId(userId);
			m.addAttribute("loginSession",loginSession);
			m.addAttribute("carts",carts);
		}
		//「cart.html」表示
		return "cart";
	}
	
	// カートの削除処理
	@SuppressWarnings("unchecked")
	@RequestMapping("/delete")
	@ResponseBody
	public boolean deleteCart(@RequestBody String checkedIdList) {
		int result = 0;
		
		//fromJsonメソッドでcheckedIdListをMap.classを用いて変換
		Map<String,List<String>> map = gson.fromJson(checkedIdList, Map.class);
		List<String> checkedIds = map.get("checkedIdList");
		for (String id : checkedIds) {
			result += cartMapper.deleteById(Integer.parseInt(id));
		}
		
		return result > 0;
		
	}
	
}
