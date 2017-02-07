package com.prosnav.utils;

import java.io.UnsupportedEncodingException;

import sun.misc.*;

public class Base64FR {
	// 加密
	public static String getBase64(String str) {
		byte[] b = null;
		String s = null;
		try {
			b = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (b != null) {
			s = new BASE64Encoder().encode(b);
		}
		return s;
	}

	// 解密
	public static String getFromBase64(String s) {
		byte[] b = null;
		String result = null;
		if (s != null) {
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				b = decoder.decodeBuffer(s);
				result = new String(b, "utf-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
//		System.out.println(getBase64("18516315054"));
//		System.out.println(getBase64("210104198707146115"));
//		System.out.println(getBase64("13555716315"));
		
		System.out.println(getBase64("18516315054"));
//		System.out.println(getFromBase64("W3siaXNzdWVuYW1lIjoiMS4g6K+36Zeu5oKo55qE5bm06b6E5Li677yaIiwiYW5zd2VyIjpbIkEuIDE45bKB5Lul5LiLJCQwIiwiQi4gMTgtMzDlsoEkJDUiLCJDLiAzMS01MOWygSQkNiIsIkQuIDUxLTY15bKBJCQzIiwiRS4g6auY5LqONjXlsoEkJDAiXX0seyJpc3N1ZW5hbWUiOiIyLiDor7fpl67mgqjnmoTlrabljobmmK/vvJoiLCJhbnN3ZXIiOlsiQS4g6auY5Lit5Y+K5Lul5LiLJCQwIiwiQi4g5Lit5LiT5oiW5aSn5LiTJCQzIiwiQy4g5pys56eRJCQ1IiwiRC4g56GV5aOr5Y+K5Lul5LiKJCQ2Il19LHsiaXNzdWVuYW1lIjoiMy4g6K+36Zeu5oKo55qE6IGM5Lia5Li677yaIiwiYW5zd2VyIjpbIkEuIOaXoOWbuuWumuiBjOS4miQkMSIsIkIuIOS4k+S4muaKgOacr+S6uuWRmCQkMyIsIkMuIOS4gOiIrOS8geS6i+S4muWNleS9jeWRmOW3pSQkNCIsIkQuIOmHkeiejeihjOS4muS4gOiIrOS7juS4muS6uuWRmCQkNiJdfSx7Imlzc3VlbmFtZSI6IjQuIOaCqOeahOWutuW6reWPr+aUr+mFjeW5tOaUtuWFpeS4uijmipjlkIjkurrmsJHluIEp77yaIiwiYW5zd2VyIjpbIkEuIDUw5LiH5YWD5Lul5LiLJCQxIiwiQi4gNTAtMTAw5LiH5YWDJCQyIiwiQy4gMTAwLTUwMOS4h+WFgyQkMyIsIkQuIDUwMC0xMDAw5LiH5YWDJCQ1IiwiRS4gMTAwMOS4h+WFg+S7peS4iiQkNiJdfSx7Imlzc3VlbmFtZSI6IjUuIOWcqOaCqOavj+W5tOeahOWutuW6reWPr+aUr+mFjeaUtuWFpeS4re+8jOWPr+eUqOS6jumHkeiejeaKlei1hCjlgqjok4TlrZjmrL7pmaTlpJYp55qE5q+U5L6L5Li677yaIiwiYW5zd2VyIjpbIkEuIDUwJeS7peS4iiQkNSIsIkIuIDI1JS01MCUkJDYiLCJDLiAxMCUtMjUlJCQ0IiwiRC4gMTAl5Lul5LiLJCQxIl19LHsiaXNzdWVuYW1lIjoiNi4g5oKo55qE5oqV6LWE55+l6K+G5Y+v5o+P6L+w5Li677yaIiwiYW5zd2VyIjpbIkEuIOaciemZkO+8muWfuuacrOayoeaciemHkeiejeaWuemdoueahOefpeivhiQkMSIsIkIuIOS4gOiIrO+8muWvuemHkeiejeS6p+WTgeWPiuWFtuebuOWFs+mjjumZqeWFt+acieWfuuacrOeahOefpeivhuWSjOeQhuinoyQkNCIsIkMuIOS4sOWvjO+8muWvuemHkeiejeS6p+WTgeWPiuWFtuebuOWFs+mjjumZqeWFt+acieS4sOWvjOeahOefpeivhuWSjOeQhuinoyQkNiJdfSx7Imlzc3VlbmFtZSI6IjcuIOaCqOeahOaKlei1hOe7j+mqjOWPr+aPj+i/sOS4uu+8miIsImFuc3dlciI6WyJBLiDpmaTpk7booYzlgqjok4TlpJbvvIzln7rmnKzmsqHmnInlhbbku5bmipXotYTnu4/pqowkJDEiLCJCLiDotK3kubDov4flgLrliLjjgIHkv53pmannrYnnkIbotKLkuqflk4EkJDMiLCJDLiDlj4LkuI7ov4fogqHnpajjgIHln7rph5HnrYnkuqflk4HnmoTkuqTmmJMkJDUiLCJELiDlj4LkuI7ov4fmnYPor4HjgIHmnJ/otKfjgIHmnJ/mnYPnrYnkuqflk4HnmoTkuqTmmJMkJDYiXX0seyJpc3N1ZW5hbWUiOiI4LiDmgqjmnInlpJrlsJHlubTmipXotYTln7rph5HjgIHogqHnpajjgIHkv6HmiZjjgIHnp4Hli5/or4HliLjmiJbph5Hono3ooY3nlJ/kuqflk4HnrYnpo47pmanmipXotYTlk4HnmoTnu4/pqow/IiwiYW5zd2VyIjpbIkEuIOayoeaciee7j+mqjCQkMSIsIkIuIOWwkeS6jjLlubQkJDIiLCJDLiAy5bm0LTXlubQkJDMiLCJELiA15bm0LTEw5bm0JCQ1IiwiRS4gMTDlubTku6XkuIokJDYiXX0seyJpc3N1ZW5hbWUiOiI5LiDmgqjorqHliJLnmoTmipXotYTmnJ/pmZDmmK/lpJrkuYU/IiwiYW5zd2VyIjpbIkEuIDHlubTku6XkuIskJDAiLCJCLiAx5bm0LTPlubQkJDMiLCJDLiAz5bm0LTXlubQkJDUiLCJELiA15bm05Lul5LiKJCQ2Il19LHsiaXNzdWVuYW1lIjoiMTAuIOaCqOeahOaKlei1hOebrueahOaYrz8iLCJhbnN3ZXIiOlsiQS4g6LWE5Lqn5L+d5YC8JCQwIiwiQi4g6LWE5Lqn56iz5YGl5aKe6ZW/JCQ0IiwiQy4g6LWE5Lqn6L+F6YCf5aKe6ZW/JCQ1Il19LHsiaXNzdWVuYW1lIjoiMTEuIOS7peS4i+WTqumhueaPj+i/sOacgOespuWQiOaCqOeahOaKlei1hOaAgeW6pj8iLCJhbnN3ZXIiOlsiQS4g5Y6M5oG26aOO6Zmp77yM5LiN5biM5pyb5pys6YeR5o2f5aSx77yM5biM5pyb6I635b6X56iz5a6a5Zue5oqlJCQwIiwiQi4g5L+d5a6I5oqV6LWE77yM5LiN5biM5pyb5pys6YeR5o2f5aSx77yM5oS/5oSP5om/5ouF5LiA5a6a5bmF5bqm55qE5pS255uK5rOi5YqoJCQyIiwiQy4g5a+75rGC6LWE6YeR55qE6L6D6auY5pS255uK5ZKM5oiQ6ZW/5oCn77yM5oS/5oSP5Li65q2k5om/5ouF5pyJ6ZmQ5pys6YeR5o2f5aSxJCQ1IiwiRC4g5biM5pyb6LWa5Y+W6auY5Zue5oql77yM5oS/5oSP5Li65q2k5om/5ouF6L6D5aSn5pys6YeR5o2f5aSxJCQ2Il19LHsiaXNzdWVuYW1lIjoiMTIuIOWBh+iuvuacieS4pOenjeaKlei1hO+8muaKlei1hEHpooTmnJ/ojrflvpcxMCXnmoTmlLbnm4rvvIzlj6/og73mib/mi4XnmoTmjZ/lpLHpnZ7luLjlsI/vvJvmipXotYRC6aKE5pyf6I635b6XMzAl55qE5pS255uK77yM5L2G5Y+v6IO95om/5ouF6L6D5aSn5LqP5o2f44CC5oKo5Lya5oCO5LmI5pSv6YWN5oKo55qE5oqV6LWE77yfIiwiYW5zd2VyIjpbIkEuIOWFqOmDqOaKlei1hOS6juaUtuebiui+g+Wwj+S4lOmjjumZqei+g+Wwj+eahEEkJDEiLCJCLiDlkIzml7bmipXotYTkuo5B5ZKMQu+8jOS9huWkp+mDqOWIhui1hOmHkeaKlei1hOS6juaUtuebiui+g+Wwj+S4lOmjjumZqei+g+Wwj+eahEEkJDIiLCJDLiDlkIzml7bmipXotYTkuo5B5ZKMQu+8jOS9huWkp+mDqOWIhui1hOmHkeaKlei1hOS6juaUtuebiui+g+Wkp+S4lOmjjumZqei+g+Wkp+eahEIkJDQiLCJELiDlhajpg6jmipXotYTkuo7mlLbnm4rovoPlpKfkuJTpo47pmanovoPlpKfnmoRCJCQ1Il19LHsiaXNzdWVuYW1lIjoiMTMuIOaCqOiupOS4uuiHquW3seiDveaJv+WPl+eahOacgOWkp+aKlei1hOaNn+WkseaYr+WkmuWwkT8iLCJhbnN3ZXIiOlsiQS4gMTAl5Lul5YaFJCQxIiwiQi4gMTAlLTMwJSQkMyIsIkMuIDMwJS01MCUkJDQiLCJELiA1MCXku6XkuIokJDUiXX0seyJpc3N1ZW5hbWUiOiIxNC4g6L+b6KGM562J5LqO5oiW5aSn5LqO5Lq65rCR5biB5LiA55m+5LiH55qE5oqV6LWE5ZCO77yM5oKo55qE5Li76KaB55qE5oSf5Y+X5piv77yaIiwiYW5zd2VyIjpbIkEuIOW+iOmrmOWFtO+8jOWvueiHquW3seeahOWGs+WumuW+iOacieS/oeW/gyQkNSIsIkIuIOi9u+advu+8jOWfuuacrOaMgeS5kOinguaAgeW6piQkNCIsIkMuIOWfuuacrOayoeS7gOS5iOW9seWTjSQkMyIsIkQuIOavlOi+g+aLheW/g+aKlei1hOe7k+aenCQkMiIsIkUuIOmdnuW4uOaLheW/g+aKlei1hOe7k+aenCQkMCJdfSx7Imlzc3VlbmFtZSI6IjE1LiDlpoLmnpzmgqjpnIDopoHmiorlpKfph4/njrDph5HmlbTlpKnmkLrluKblnKjouqvvvIzmgqjmmK/lkKbkvJrmhJ/liLDvvJoiLCJhbnN3ZXIiOlsiQS4g6Z2e5bi454Sm6JmRJCQwIiwiQi4g5pyJ54K554Sm6JmRJCQzIiwiQy4g5a6M5YWo5LiN5Lya54Sm6JmRJCQ1Il19LHsiaXNzdWVuYW1lIjoiMTYuIOW9k+aCqOeLrOiHquWIsOWkluWcsOa4uOeOqe+8jOmBh+WIsOS4ieWylOi3r+WPo++8jOaCqOS8mumAieaLqe+8miIsImFuc3dlciI6WyJBLiDku5Tnu4bnoJTnqbblnLDlm77lkozot6/moIckJDUiLCJCLiDmib7liKvkurrpl67ot68kJDQiLCJDLiDlpKfoh7TliKTmlq3kuIDkuIvmlrnlkJEkJDMiLCJELiDkuZ/orrjkvJrnlKjmjrfpqrDlrZDnmoTmlrnlvI/lhrPlrpokJDEiXX0seyJpc3N1ZW5hbWUiOiIxNy4g5YGH5aaC5oKo5YmN5pyf55SoMjXlhYPotK3lhaXkuIDmlK/ogqHnpajvvIzor6XogqHnpajnjrDlnKjljYfliLAzMOWFg++8jOiAjOagueaNrumihOa1i+ivpeiCoei/keacn+acieS4gOWNiuacuuS8muWNh+WIsDM15YWD77yM5Y+m5LiA5Y2K5py65Lya6LeM5YiwMjXlhYPvvIzmgqjnjrDlnKjkvJrvvJoiLCJhbnN3ZXIiOlsiQS4g56uL5Yi75Y2W5Ye6JCQxIiwiQi4g6YOo5YiG5Y2W5Ye6JCQyIiwiQy4g57un57ut5oyB5pyJJCQ0IiwiRC4g57un57ut5Lmw5YWlJCQ1Il19LHsiaXNzdWVuYW1lIjoiMTguIOWQjOS4iumimOaDheWGte+8jOivpeiCoeeOsOWcqOW3sue7j+i3jOWIsDIw5YWD77yM6ICM5oKo5Lyw6K6h6K+l6IKh6L+R5pyf5pyJ5LiA5Y2K5py65Lya5Y2H5ZueMjXlhYPvvIzlj6bkuIDljYrmnLrkvJrnu6fnu63kuIvot4zliLAxNeWFg++8jOaCqOeOsOWcqOS8mu+8miIsImFuc3dlciI6WyJBLiDnq4vliLvljZblh7okJDEiLCJCLiDpg6jliIbljZblh7okJDIiLCJDLiDnu6fnu63mjIHmnIkkJDQiLCJELiDnu6fnu63kubDlhaUkJDUiXX1d"));
		
//		System.out.println(getBase64("[{\"issuename\":\"1. 请问您的年龄为：\",\"item\":\"6\",\"answer\":[\"A. 18岁以下$$0\",\"B. 18-30岁$$5\",\"C. 31-50岁$$6\",\"D. 51-65岁$$3\",\"E. 高于65岁$$0\"]},{\"issuename\":\"2. 请问您的学历是：\",\"item\":\"6\",\"answer\":[\"A. 高中及以下$$0\",\"B. 中专或大专$$3\",\"C. 本科$$5\",\"D. 硕士及以上$$6\"]},{\"issuename\":\"3. 请问您的职业为：\",\"item\":\"4\",\"answer\":[\"A. 无固定职业$$1\",\"B. 专业技术人员$$3\",\"C. 一般企事业单位员工$$4\",\"D. 金融行业一般从业人员$$6\"]},{\"issuename\":\"4. 您的家庭可支配年收入为(折合人民币)：\",\"item\":\"6\",\"answer\":[\"A. 50万元以下$$1\",\"B. 50-100万元$$2\",\"C. 100-500万元$$3\",\"D. 500-1000万元$$5\",\"E. 1000万元以上$$6\"]},{\"issuename\":\"5. 在您每年的家庭可支配收入中，可用于金融投资(储蓄存款除外)的比例为：\",\"item\":\"4\",\"answer\":[\"A. 50%以上$$5\",\"B. 25%-50%$$6\",\"C. 10%-25%$$4\",\"D. 10%以下$$1\"]},{\"issuename\":\"6. 您的投资知识可描述为：\",\"item\":\"6\",\"answer\":[\"A. 有限：基本没有金融方面的知识$$1\",\"B. 一般：对金融产品及其相关风险具有基本的知识和理解$$4\",\"C. 丰富：对金融产品及其相关风险具有丰富的知识和理解$$6\"]},{\"issuename\":\"7. 您的投资经验可描述为：\",\"answer\":[\"A. 除银行储蓄外，基本没有其他投资经验$$1\",\"B. 购买过债券、保险等理财产品$$3\",\"C. 参与过股票、基金等产品的交易$$5\",\"D. 参与过权证、期货、期权等产品的交易$$6\"]},{\"issuename\":\"8. 您有多少年投资基金、股票、信托、私募证券或金融衍生产品等风险投资品的经验?\",\"item\":\"5\",\"answer\":[\"A. 没有经验$$1\",\"B. 少于2年$$2\",\"C. 2年-5年$$3\",\"D. 5年-10年$$5\",\"E. 10年以上$$6\"]},{\"issuename\":\"9. 您计划的投资期限是多久?\",\"item\":\"5\",\"answer\":[\"A. 1年以下$$0\",\"B. 1年-3年$$3\",\"C. 3年-5年$$5\",\"D. 5年以上$$6\"]},{\"issuename\":\"10. 您的投资目的是?\",\"item\":\"5\",\"answer\":[\"A. 资产保值$$0\",\"B. 资产稳健增长$$4\",\"C. 资产迅速增长$$5\"]},{\"issuename\":\"11. 以下哪项描述最符合您的投资态度?\",\"item\":\"2\",\"answer\":[\"A. 厌恶风险，不希望本金损失，希望获得稳定回报$$0\",\"B. 保守投资，不希望本金损失，愿意承担一定幅度的收益波动$$2\",\"C. 寻求资金的较高收益和成长性，愿意为此承担有限本金损失$$5\",\"D. 希望赚取高回报，愿意为此承担较大本金损失$$6\"]},{\"issuename\":\"12. 假设有两种投资：投资A预期获得10%的收益，可能承担的损失非常小；投资B预期获得30%的收益，但可能承担较大亏损。您会怎么支配您的投资？\",\"item\":\"2\",\"answer\":[\"A. 全部投资于收益较小且风险较小的A$$1\",\"B. 同时投资于A和B，但大部分资金投资于收益较小且风险较小的A$$2\",\"C. 同时投资于A和B，但大部分资金投资于收益较大且风险较大的B$$4\",\"D. 全部投资于收益较大且风险较大的B$$5\"]},{\"issuename\":\"13. 您认为自己能承受的最大投资损失是多少?\",\"answer\":[\"A. 10%以内$$1\",\"B. 10%-30%$$3\",\"C. 30%-50%$$4\",\"D. 50%以上$$5\"]},{\"issuename\":\"14. 进行等于或大于人民币一百万的投资后，您的主要的感受是：\",\"answer\":[\"A. 很高兴，对自己的决定很有信心$$5\",\"B. 轻松，基本持乐观态度$$4\",\"C. 基本没什么影响$$3\",\"D. 比较担心投资结果$$2\",\"E. 非常担心投资结果$$0\"]},{\"issuename\":\"15. 如果您需要把大量现金整天携带在身，您是否会感到：\",\"answer\":[\"A. 非常焦虑$$0\",\"B. 有点焦虑$$3\",\"C. 完全不会焦虑$$5\"]},{\"issuename\":\"16. 当您独自到外地游玩，遇到三岔路口，您会选择：\",\"answer\":[\"A. 仔细研究地图和路标$$5\",\"B. 找别人问路$$4\",\"C. 大致判断一下方向$$3\",\"D. 也许会用掷骰子的方式决定$$1\"]},{\"issuename\":\"17. 假如您前期用25元购入一支股票，该股票现在升到30元，而根据预测该股近期有一半机会升到35元，另一半机会跌到25元，您现在会：\",\"answer\":[\"A. 立刻卖出$$1\",\"B. 部分卖出$$2\",\"C. 继续持有$$4\",\"D. 继续买入$$5\"]},{\"issuename\":\"18. 同上题情况，该股现在已经跌到20元，而您估计该股近期有一半机会升回25元，另一半机会继续下跌到15元，您现在会：\",\"answer\":[\"A. 立刻卖出$$1\",\"B. 部分卖出$$2\",\"C. 继续持有$$4\",\"D. 继续买入$$5\"]},{\"score\":\"77\"}]"));
	}
}