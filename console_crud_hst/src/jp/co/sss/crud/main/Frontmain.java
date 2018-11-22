package jp.co.sss.crud.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jp.co.sss.crud.db.DBManager;
import jp.co.sss.crud.util.ScreenMenu;

public class Frontmain {

	public static void main(String[] args) throws IOException {
		//	処理繰返し区分のフラッグ
		int FlowFlag = 0;
		ScreenMenu sc = new ScreenMenu();
//		Messages msg = new Messages();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (FlowFlag == 0) {
			sc.MainMenu();
			sc.InputNum();
			int MenuNum = Integer.parseInt(br.readLine());
			try {
				switch (MenuNum) {
				case 1:
					DBManager.AllSearch();
					break;
				case 2:
					DBManager.SpecialNameSearch();
					break;
				case 3:
					DBManager.InsertData();
					break;
				case 4:
					DBManager.UpdateData();
					break;
				case 5:
					DBManager.DeleteData();
					break;
				case 6:
					FlowFlag = 1;
					sc.EndMessages();
					break;
				default:
					FlowFlag = 1;
					sc.ErrorMessages();
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
