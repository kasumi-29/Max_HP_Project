# 最大HP調整プラグイン
## 概要
MinecraftのBukkit/Spigotサーバーで動くプラグインです。    
このプラグインは、自作プラグイン「 [俺の屍を超えられない プラグイン](https://github.com/kasumi-29/over_my_Dead_body) 」の
README.md で
  
> 縛りをキツくしたい場合は、スポーン時のHPを調整が出来る他プラグインと組み合わせたり  

と書いたものの、軽く調べた感じでは最大HPを調整できるシンプルなプラグインが見当たらなかったため、自作したものです。  
最大の特徴は、シンプルさにあります。このプラグインでできることは、
- プレイヤーのデフォルトの最大HPを設定すること
- ひとりひとりのプレイヤーに対して最大HPを設定すること

のみです。  
このプラグインで言う「HP」とはハート0.5個分を1とした値のことです。
つまりデフォルトのHPは20です。

## 追加されるコマンド
このプラグインでは3つのコマンドが追加されます。
- set-default-hp  
**使い方**： `/set-default-hp <最大HP>`  
**実行可能**： OP  
**説明**： このコマンドを使用すると、現在接続中のプレイヤーに対し最大HPを設定します。
これは今後ログインしてくるプレイヤーにも適応されます。ただしカスタムHPが設定されているプレイヤーには影響されません。  
**実行例**： `/set-default-hp 10`

- set-hp  
**使い方**: `/set-hp [Player] <最大HP>`  
**実行可能**： OP  
**説明**： プレイヤーごとにカスタムHPを設定します。ここで設定されたプレイヤーは `config.yml` から削除しない限り
`set-default-hp` コマンドの影響を受けません。  
**実行例**： `/set-hp famas1219 5`

- super-effect  
**使い方**： `/effect` コマンドに準じる  
**実行可能**： OP  
**説明**： マインクラフトのバニラに存在する `/effect` コマンドと同じ引数を取り、同じ動作をします。
ただしTabキーによる補完は動作しません。また、このコマンドの使用は最小限に留めてください。詳細については「health_boostについて」節を参照してください。  
**実行例**： `/super-effect give @a minecraft:health_boost 10000 3`

## health_boostについて
このプラグインでは `effect` コマンドのうち、 `health_boost` を付与するコマンドのみブロックしており、実行することが出来ません。  
これは、最大HPが通常よりも少ない状態で `health_boost` を付与し、その後、時間経過により解除されると体力が残っているにも関わらず
リスポーン画面が表示され、また プレイヤーの表示もバグった表示になります。これを防ぐため `health_boost` の付与を制限しています。  
なお、 `health_boost` の付与以外については `effect` コマンドが通常通り使用できます。また、サバイバルで入手可能な「金りんご」等も
一時的に体力を増加させますが、同様のバグは発生しないことを確認済です。  

ただしPVPマップ等でこのプラグインを利用し、コマンドブロック等で `effect` コマンドを付与する際は、 `super-effect` 
コマンドを実行の上、付与秒数を十分大きい値に設定してください。

## 遊び方
- 単純に、サバイバルで最大HPを減らして、縛りプレイで遊ぶのも良いでしょう。さらに難易度を挙げたい場合、ハードコアモードと組み合わせるのも良いかもしれません。
- PVPマップとの組み合わせも良いかもしれません。このプラグインではカスタムHPを個々のプレイヤーに対して設定できるため、
大将・中将・歩兵を設定し、それぞれHPが違う、といったPVP戦も考えられます。
- さらに「 [俺の屍を超えられない プラグイン](https://github.com/kasumi-29/over_my_Dead_body) 」の難易度調整にも利用できます。

## 推奨環境
- まだテスト中です

## ダウンロード
現在開発中です
- [ ~~ダウンロードはこちら~~ ](https://github.com/kasumi-29/Max_HP_Project/releases/tag/v2.1.0)