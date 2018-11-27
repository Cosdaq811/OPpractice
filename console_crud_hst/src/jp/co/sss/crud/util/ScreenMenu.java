package jp.co.sss.crud.util;

//	2018/11/27	gitの練習のため、一部メッセージ内容修正
//	2018/11/27	branch追加、Ｍｅｒｇｅ

public class ScreenMenu {

	//	メインメニューの表示
	public void InputNum(){
		System.out.println("ブンブン会社の社員管理システムです。");
		System.out.print("メニューNUMBERを入力してください:");
	}

	//	メニュー表示
	public void MainMenu() {
		System.out.println("=== 社員管理システム ===");
		System.out.println("1. 全件表示");
		System.out.println("2. 社員名検索");
		System.out.println("3. 登録");
		System.out.println("4. 更新");
		System.out.println("5. 削除");
		System.out.println("6. 終了");
	}

	//	データ検索時、カラム行表示
	public void DataColumnRow() {
		System.out.print("社員ID"+ "\t");
		System.out.print("社員名"+ "   " +"\t");
		System.out.print("性別"+ "   " + "\t");
		System.out.print("生年月日"+ "       " + "\t");
		System.out.println("部署名");
		System.out.println("===================================================");
	}

	//	データが０件の場合
	public void NoData() {
		System.out.println("社員が登録されていません");
	}

	public void InsertData1() {
		System.out.print("社員名:");
	}

	public void InsertData2() {
		System.out.print("性別(1: 男性, 2: 女性):");
	}

	public void InsertData3() {
		System.out.print("生年月日（西暦年/月/日）:");
	}

	public void InsertData4() {
		System.out.print("部署ID(1：営業部、2：経理部、3：総務部):");
	}

	//	登録完了
	public void InsertData5() {
		System.out.println("社員情報を登録しました");
	}

	//	更新対象のデータ検索
	public void UpdateData1() {
		System.out.print("更新する社員の社員IDを入力してください:");
	}

	//	更新完了
	public void UpdateData2() {
		System.out.println("社員情報を更新しました");
	}

	//	削除対象のデータ検索
	public void DeleteData1() {
		System.out.print("削除する社員の社員IDを入力してください:");
	}

	//	削除完了
	public void DeleteData2() {
		System.out.println("社員情報を削除しました");
	}

	//	更新または、削除時該当データなし
	public void DataNotFound() {
		System.out.println("該当する社員は登録されていません");
	}

	//	社員名検索
	public void SpecialNameSearch1(){
		System.out.println("社員名を入力してください");
	}

	//	社員名検索でデータなし
	public void SNameNoData() {
		System.out.println("該当する社員は存在しません");
	}

	//	正常終了
	public void EndMessages() {
		System.out.println("システムを終了します");
	}

	//	エラー終了
	public void ErrorMessages() {
		System.out.println("システムエラーが発生しました");
		System.out.println("システムを終了します");
	}
}
