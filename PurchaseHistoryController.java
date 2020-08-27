package jp.co.internous.altair.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.altair.model.domain.dto.PurchaseHistoryDto;
import jp.co.internous.altair.model.mapper.TblPurchaseHistoryMapper;
import jp.co.internous.altair.model.session.LoginSession;

@Controller
@RequestMapping("/altair/history")
public class PurchaseHistoryController {
	
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	TblPurchaseHistoryMapper purchaseHistoryMapper;
	
	// 商品購入履歴を表示
	@RequestMapping("/")
	public String index(Model m) {	
		// loginSessionからユーザーの情報を取得
		int userId = loginSession.getUserId();
		// userIdに紐づく履歴情報を取得し表示
		List<PurchaseHistoryDto> historyList = purchaseHistoryMapper.findByUserId(userId);
		m.addAttribute("historyList",historyList);
		m.addAttribute("loginSession", loginSession);
		
		return "purchase_history";
	}
	
	// 購入履歴の削除処理
	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete() {
		// loginSessionからユーザーの情報を取得
		int userId = loginSession.getUserId();
		// 購入履歴を論理削除
		int result = purchaseHistoryMapper.logicalDeleteByUserId(userId);
		
		return result > 0;
	}
}