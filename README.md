# 概要
SpringBootによる下記の機能をもつ業務日付アプリを作成しました。

# 機能
業務日付（月末など）を管理します。
+ 業務日付を、入力した計算基準日を基に計算。
+ 登録済みデータを一覧で確認。一覧ページでは削除・更新ができる。
+ WebAPIで一覧データ、個別データを取得。通信フォーマットはJSON。

# テスト
+ JUnit4によるユニットテスト
+ Seleniedによる画面テスト

# 動作環境
+ Amazon Linux2
+ RDS（MySQL 8.0.19）
+ Tomcat 9.35
+ JDK 1.8
+ gradle 6.0

# 導入方法
まず、実行環境上で```datemanager/src/main/resources/application.properties```内の下記を環境に合わせて書き換えます。
+ spring.datasource.url
+ spring.datasource.username
+ spring.datasource.password

次に、```./gradlew build```を実行し、作成されたsample.warをTomcatのフォルダのwebapps内に配置します。
