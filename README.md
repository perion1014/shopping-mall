<div align="center">
<h1>Project PERION</h1>
<p>
  当プロジェクトは「PERION」といったメンズファッション通販サイトです。<br>
  通販サイトを選定した理由としては、CRUD, session, ファイル処理、マークアップなどITスクールで学習したものを活かすことができ、<br>
「基本に充実する」といった共通の目的に一致したためです。<br>
　PERIONは韓国のMMORPGに登場する戦士の村で、「戦士のように、強い気持ちで生きていきたい」といった気持ちを込め、名付けました。
</p>
</div>

## 全体目次
- [プロジェクト概要](#プロジェクト概要)
- [プロジェクト説明](#プロジェクト説明)
- [リファクタリング](#リファクタリング)
- [参考資料](#参考資料)


## プロジェクト概要
- プロジェクト名: Perion
- プロジェクト期間: 2023.12.05-2024.01.08 （約1か月）
- プロジェクト技術：
  + Backend<br>
    <img src="https://img.shields.io/badge/Java-1C9AD6?style=flat-square&logo=Java&logoColor=orange"/>
    <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/>
    <img src="https://img.shields.io/badge/MyBatis-030303?style=flat-square&logo=MyBatis&logoColor=white"/>
    <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=springboot&logoColor=white"/>
    <img src="https://img.shields.io/badge/Gradle-02303A?style=flat-square&logo=Gradle&logoColor=white"/>
    <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=flat-square&logo=Thymeleaf&logoColor=white"/>
    <img src="https://img.shields.io/badge/Lombok-EC1C24?style=flat-square&logo=Lombok&logoColor=white"/>
  + Frontend<br>
    <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=HTML5&logoColor=white"/>
    <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=CSS3&logoColor=white"/>
    <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=JavaScript&logoColor=black"/>
    <img src="https://img.shields.io/badge/SweetAlert2-EF1970?style=flat-square&logo=SweetAlert&logoColor=white"/>

  + Environment<br>
    <img src="https://img.shields.io/badge/IntelliJ-000000?style=flat-square&logo=IntelliJIdea&logoColor=white"/>
    <img src="https://img.shields.io/badge/Visual Studio Code-007ACC?style=flat-square&logo=VisualStudioCode&logoColor=white"/>
    <img src="https://img.shields.io/badge/Figma-F24E1E?style=flat-square&logo=Figma&logoColor=white"/>
    <img src="https://img.shields.io/badge/Git-F05032?style=flat-square&logo=Git&logoColor=white"/>
    <img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=GitHub&logoColor=white"/>
    <img src="https://img.shields.io/badge/ERDCloud-181717?style=flat-square&logo=ERDCloud&logoColor=white"/>
    <img src="https://img.shields.io/badge/Google Spreadsheets-34A853?style=flat-square&logo=Google Sheets&logoColor=white"/>
    
- チームメンバー:
  + クォン・ヒョンモ（PM/BE/FE）[@HyonHyonKOR](https://github.com/HyonHyonKOR) <br><br>
  UX/UIデザイン総括 / クライアントページマークアップ・FE / サーバーアーキテクチャ / 会員機能全般 / バリデーションチェック / 認証（Spring Interceptor）<br><br>
  + キム・チャンヒョク(BE/FE)  [@swaeluu](https://github.com/swaeluu) <br><br>
  UX/UIデザイン / 管理者ページマークアップ・FE / Q&A機能　/　カスタマーレビュー機能　/　ページング処理<br><br>
  + パク・ヨンム(BE/FE)　[@VoiceofSiren](https://github.com/VoiceofSiren) <br><br>
  AJAX / 商品関連機能　/　在庫管理機能　/　会員カート機能　/　会員注文機能　/　DBA <br><br>
  + イ・ボムソン(BE/FE) [@Titan153](https://github.com/Titan153)　<br><br>
  お知らせ機能　/　ID・PW再設定機能　/ マークアップ補助　/ 外部API　/　参考資料整理　<br><br> 
  + チャン・ミョンソン(BE/FE)　[@Ainchel](https://github.com/Ainchel)　<br><br>
  AJAX / 在庫管理機能　/　会員カート機能　/　非会員カート機能　/　非会員注文機能　 <br><br>

## プロジェクト説明
※[プロジェクトの詳しい説明と過程につきましてはこちらより](https://zenn.dev/eldorado215) 

### フロントエンド

  + Responsive WEB
<table>
  <thead>
    <tr>
      <th align="center">Desktop</th>
      <th align="center">tablet</th>
      <th align="center">mobile</th>
    </tr>
  <tbody>
    <tr>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/89d0dfc2-acab-4fe7-8bc6-1d8e7eaaa42c" width="400px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/aa90bc71-9002-4525-a054-6c33bb227e78" width="300px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/4ba646da-66f0-4db3-be4a-cc20ac4e731a" width="200px;" alt=""/></td>
     <tr/>
    <tr>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/9f4ba2e6-08f8-41b2-b334-26409b8d4ec2" width="400px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/1dfbca9c-ba2c-46f2-b13b-78fd070115ca" width="300px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/f8e0e3ec-0230-44c1-a95a-a2587b162e6d" width="200px;" alt=""/></td>
    </tr>
     <tr>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/d5ffd3c7-f868-41f5-bd67-b70266e92a0d" width="400px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/131c7a86-6ebc-4344-ae12-23bdc2b06ffc" width="300px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/338e6ccb-29b9-49d0-9fad-c4a9f7aa1382" width="200px;" alt=""/></td>
    </tr>
     <tr>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/73007a60-37b8-47a8-a7aa-8ee931359300" width="400px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/e2faeca0-941b-4e87-87aa-e1969ef23d2b" width="300px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/6edba50e-e182-4193-bab5-dc1b49c26e9c" width="200px;" alt=""/></td>
    </tr>
     <tr>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/6fbfcf75-0390-40cc-8d26-e8a7b52c1311" width="400px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/488be4dc-5e3f-46c3-815d-30a06ccacbd9" width="300px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/f937bf6a-9a3c-4ccb-b309-6cfba8f3ed9e" width="200px;" alt=""/></td>
    </tr>
     <tr>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/02b7a1aa-badf-4077-9458-b2bfb481ede6" width="300px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/5cdcf06c-3be5-4135-9601-ad6182d1ade0" width="300px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/95a06d98-20c8-42b4-924f-5f631d60b653" width="200px;" alt=""/></td>
    </tr>
  </tbody>
</table>

  最初のページは、viewportのサイズによって、レイアウトが変更されるレスポンシブWEBを具象しました。<br>
  今後、indexページのみならず、他のページもmedia queryを活用して、レスポンシブWEBにしたいと思います。<br><br>

  + UX/UI

  <table>
  <thead>
    <tr>
      <th align="center">Navigation</th>
      <th align="center">Side bar</th>
      <th align="center">login(未入力)</th>
      <th align="center">login(入力後)</th>
    </tr>
  <tbody>
    <tr>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/2bb098cc-c753-41dc-ba3e-a566b524e12f" width="300px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/a06c5094-81a4-4856-ab8a-4030d4f2bd21" width="300px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/b6035dbe-9741-4e65-913e-f1edf1acbddb" width="300px;" alt=""/></td>
      <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/808c9e7a-69f5-4596-9300-83ee70882e2b" width="300px;" alt=""/></td>
     <tr/>
  </tbody>
</table>

  1. Navigationからカテゴリーをすぐ選択できるようにし、ページDEPTHを減らすことでCPRの向上を目指しました。<br>
  2. 縦が長いページであるため、ユーザーが欲しい情報をすぐ確認できるよう、サイドバーを具象しました。<br>
  3. より直感的にログインができるよう、IDとパスワードの最低文字数を超えれば、ログインボターンのOpacityが１になるように調節しました。<br><br>

  + AJAX
　  <table>
      <thead>
        <tr>
        <th align="center">商品詳細</th>
        <th align="center">カート</th>
      </tr>
    <tbody>
      <tr>
        <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/5a141cba-4bd7-4d56-be76-864e6b721628" width="500px;" alt=""/></td>
        <td align="center"><img src="https://github.com/HyonHyonKOR/team-project/assets/134394081/af7a73e7-40f4-4a23-8f17-f6f53eea2831" width="500px;" alt=""/></td>
       <tr/>
    </tbody>
  </table>
    
   JavaScriptのfetch、JSON、SpringBootの＠ResponseBodyアノテーションなどを活用し、カートを具象しました。<br>


### バックエンド
  + 商品関連
  + 注文
  + 管理者ページ
  + 認証(Spring Interceptor)


## リファクタリング
- 近いうちにAWSへのdeployを計画中
- 会員登録機能にAJAXを追加し、フロントエンドからもバリデーションチェックを追加
- ページ処理のバグを改善する予定

## 参考資料
- 商品写真360枚: https://www.coor.kr/
- インデックスページのイメージ: https://unsplash.com/ko
- ロゴ: https://www.figma.com/
- Kakao Map API, Kakao アドレスAPI : https://developers.kakao.com/
- Sweetalert2: https://sweetalert2.github.io/
- SVGおよびフォント: https://fonts.google.com/








