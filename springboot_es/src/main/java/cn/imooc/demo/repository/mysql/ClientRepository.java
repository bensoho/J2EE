package cn.imooc.demo.repository.mysql;

import cn.imooc.demo.entity.mysql.ClientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientInfo,Integer> {
}
