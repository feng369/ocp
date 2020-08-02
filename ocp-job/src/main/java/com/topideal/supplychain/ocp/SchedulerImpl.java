package com.topideal.supplychain.ocp;

import com.topideal.supplychain.schedule.MyScheduler;
import org.quartz.Job;
import org.springframework.stereotype.Component;

@Component
public class SchedulerImpl extends MyScheduler {

    @Override
    public void scheduleJobs() throws Exception {
        super.scheduleJobs();

        startJob("达令家定时漏单抓单", DaLingGetMissOrderJob.class,  "0 */5 * * * ?");
        startJob("达令家定时抓单", DaLingGetOrderJob.class,  "0 */5 * * * ?");
        startJob("有赞定时抓单", YouZanGetOrderJob.class,  "0 */5 * * * ?");
        startJob("唯品会定时抓单", VipGetOrderJob.class,  "0 */10 * * * ?");
        startJob("有赞漏单定时抓单", YouZanGetMissOrderJob.class,  "0 0 8 * * ?");
        startJob("全球仓定时抓单", GlobalGetOrderJob.class,  "0 0 8 * * ?");
        startJob("全球仓漏单定时抓取", GlobalGetMissOrderJob.class,  "0 0 8 * * ?");
        startJob("洋码头定时抓单", YmatouGetOrderJob.class,  "0 */5 * * * ?");
        startJob("拼多多定时抓单", PddGetOrderJob.class,  "0 /3 * * * ? ");
        startJob("拼多多24小时漏单定时抓单", PddGetMissOrderJob.class,  "0 0 8 * * ?");
        startJob("宝妈时光定时抓单", BaomaGetOrderJob.class,  "0 */5 * * * ?");
        startJob("小米定时抓单", XiaomiGetOrderJob.class,  "0 */5 * * * ?");
        startJob("小米漏单定时抓单", XiaomiGetMissOrderJob.class,  "0 0 8 * * ?");
        startJob("京东自营非自营定时抓单", JdGetOrderJob.class,  "0 */5 * * * ?");
        startJob("京东云霄购定时抓单", JdGetYxOrderJob.class,  "0 */5 * * * ?");
        startJob("京东多渠道独立站定时抓单", JdGetDlzOrderJob.class,  "0 */5 * * * ?");
        startJob("环球捕手定时抓单", GsGetOrderJob.class,  "0 */5 * * * ?");
        startJob("贝贝定时抓单", BeibeiGetOrderJob.class,  "0 */5 * * * ?");
        startJob("丁香医生定时抓单", DxyGetOrderJob.class,  "0 */5 * * * ?");
        startJob("丁香医生漏单定时抓单", DxyGetMissOrderJob.class,  "0 0 7 * * ?");

    }

    @Override
    public void addJob(String jobName, Class<? extends Job> clazz,String cron) throws Exception {
        startJob(jobName, clazz, cron);
    }
}
