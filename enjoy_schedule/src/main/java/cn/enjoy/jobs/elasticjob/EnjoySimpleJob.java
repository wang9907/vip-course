package cn.enjoy.jobs.elasticjob;

import cn.enjoy.jobs.business.EnjoyBusiness;
import com.cxytiandi.elasticjob.annotation.ElasticJobConf;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.springframework.beans.factory.annotation.Autowired;

@ElasticJobConf(name = "EnjoyJob") //跟配置里的elastic.job.EnjoyJob对应
public class EnjoySimpleJob implements SimpleJob {
	@Autowired
	private EnjoyBusiness enjoyBusiness;

	public void execute(ShardingContext context) {
		System.out.println("当前分片："+context.getShardingParameter());
		int total = context.getShardingTotalCount();
		int shardingItem = context.getShardingItem();

		enjoyBusiness.process(shardingItem,total,"");
	}

}