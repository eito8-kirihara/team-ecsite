
package jp.co.internous.altair.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.altair.model.domain.TblCart;
import jp.co.internous.altair.model.domain.dto.CartDto;


@Mapper
public interface TblCartMapper {
	
	// カート情報取得
	List<CartDto> findByUserId(@Param("userId") int userId);
	
	// カート情報を追加	
	@Insert("INSERT INTO tbl_cart ("
			+ "user_id, product_id, product_count "
			+ ") "
			+ "VALUES ("
			+ "#{userId}, #{productId}, #{productCount} "
			+ ")")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insert(TblCart cart);
	
	// 商品個数と合算
	@Update("UPDATE tbl_cart SET product_count = product_count + #{productCount}, updated_at = now() WHERE user_id = #{userId} AND product_id = #{productId}")
	int update(TblCart cart);
	
	// ユーザーIDを取得
	@Select("SELECT count(user_id) FROM tbl_cart WHERE user_id = #{userId}")
	int findCountByUserId(@Param("userId") int userId);
	
	// 仮ユーザーIDからログイン後のユーザーIDに更新
	@Update("UPDATE tbl_cart SET user_id = #{userId}, updated_at = now() WHERE user_id = #{tmpUserId}")
	int updateUserId(@Param("userId") int userId, @Param("tmpUserId") int tmpUserId);
	
	// カート商品削除のため、ユーザーIDと商品IDを取得
	@Select("SELECT count(id) FROM tbl_cart WHERE user_id = #{userId} AND product_id = #{productId}")
	int findCountByUserIdAndProuductId(@Param("userId") int userId, @Param("productId") int productId);

	// カートから削除
	@Delete("DELETE FROM tbl_cart WHERE id = #{id}")
	int deleteById(@Param("id") int id);
	
	// 決済時、カートを削除
	@Delete("DELETE FROM tbl_cart WHERE user_id = #{userId}")
	int deleteByUserId(@Param("userId") int userId);
}
