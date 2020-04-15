# ktor-appengine-java11

Kotlinの非同期WebアプリケーションフレームワークであるktorをAppEngine Java11で動かすサンプルです。

# ローカルでの実行方法

1. ./gradlew run
2. http://localhost:8080 を開く


# デプロイ方法

1. build.gradle.ktsのappengine.deploy.projectIdを自身の作成したGoogle AppEngineプロジェクトIDに
  変更
2. ./gradlew appengineDeploy
