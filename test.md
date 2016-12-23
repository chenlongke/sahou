# 会员卡中心数据迁移
------
本迁移方案适用于会员卡中心，即从将原数据从vacn_db迁移到user_center等一系列解决方案
------
`会员卡数据迁移` `陈龙科<chenlonkge@xf9.com>`
> * 迁移解决方案 http://192.168.3.58:8080/svn/monitor/DATA_TRANSFER_ITEM/CardCenter

### 本次迁移涉及vacn_db数据表

> * 16860_card_bind_relation
> * 16860_card_fee
> * 16860_card_log
> * 16860_member_card_bd_relation
> * 16860_operate_log
> * 16860_card_order
> * 16860_card_type
> * 16860_member_cards
> * 16860_rechage_log
> * 16860_membercard_refund
> * 16860_membercard_refund_config
> * 16860_membercard_refund_doc
> * <s>16860_membercard_refund_error(废弃)</s>
> * <s>16860_membercard_refund_log(废弃)</s>
> * <s>16860_tcaccount_relation(废弃)</s>
> * <s>16860_card_upgrade(废弃)</s>
> * <s>16860_recharge_type(废弃)</s>


### user_center新表
> * uc_user_card
> * uc_user_card_log
> * uc_trade_order
> * uc_card_type
> * uc_card
> * uc_card_trade_log
> * uc_refund_order
> * uc_conf
> * uc_process_task
> * uc_process_step



### 迁移流程
```flow
st=>start: 开始
op1=>operation: 执行SQL目录下sql文件，一次执行
op2=>operation: 执行script目录下php文件，依次执行
e=>end: 结束
cond=>condition: 迁移正确?
st->op1->op2->cond
cond(yes)->e
cond(no)->op1
```
### php 配置文件说明
```php
/* 数据库配置*/
define("DBUSER", "admin");//用户确保有读写权限
define("DBPWD", "ser4w2e10p");
define("DBDSN", "mysql:host=192.168.3.73;dbname=vacn_db;charset=utf8;port=3307");

/*脚本每次处理记录限量*/
define("DB_SEARCH_LIMIT",2000);
```
### 说明
> * 线上要先确保user_center下相关的表应该存在；如果没有可执行 `SQL/cardTable.sql` 文件,文件会自动创建表
> * 执行php脚本可能会中途中断，这个没有问题；重新执行脚本就可以了

### SQL文件说明
<table>
    <thead>
        <tr>
            <th>文件</th>
            <th>说明</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>1_uc_conf.sql</td>
            <td>退款配置脚本</td>
        </tr>
        <tr>
            <td>2_uc_card_type.sql</td>
            <td>卡类型数据脚本</td>
        </tr>
        <tr>
            <td>3_uc_card.sql</td>
            <td>会员卡数据脚本</td>
        </tr>
        <tr>
            <td>4_uc_card_service.sql</td>
            <td>卡服务数据脚本</td>
        </tr>
        <tr>
            <td>5_uc_user_card.sql</td>
            <td>会员卡绑定信息数据脚本</td>
        </tr>
        <tr>
            <td>6_uc_user_card_log.sql</td>
            <td>会员卡绑定操作日志数据脚本</td>
        </tr>
        <tr>
            <td>7_uc_card_trade_log.sql</td>
            <td>卡金额日志数据脚本</td>
        </tr>
        <tr>
            <td>8_uc_trade_order.sql</td>
            <td>会员卡订单数据脚本</td>
        </tr>
        <tr>
            <td>9_refund.sql</td>
            <td>退款数据脚本</td>
        </tr>
        <tr>
            <td>10_update.sql</td>
            <td>字段修补更新数据脚本</td>
        </tr>
        <tr>
            <td>cardTable.sql</td>
            <td>表结构</td>
        </tr>
    </tbody>
</table>
### SQL文件说明
| 文件 | 说明  | 
|:---- |:----- |
|config.php| 配置文件|
|db.php|数据库操作类|
|1_uc_card_trade_log.php|数据修复脚本|



| Item      |    Value | Qty  |
| :-------- | :--------| :--: |
| Computer  | 1600 USD |  5   |
| Phone     |   12 USD |  12  |
| Pipe      |    1 USD | 234  |
