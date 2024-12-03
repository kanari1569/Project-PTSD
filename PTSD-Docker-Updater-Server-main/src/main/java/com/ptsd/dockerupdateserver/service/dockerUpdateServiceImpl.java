package com.ptsd.dockerupdateserver.service;

import com.ptsd.dockerupdateserver.dto.responseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Service
public class dockerUpdateServiceImpl implements dockerUpdateService{

    private static final String HUB_TAG = "projectptsd/";
    static int cnt = 1;

    @Override
    public ResponseEntity<responseDto> rollingUpdate(String imageName) {


        try{

            String[] strArr = imageName.split(":");

            String pullCommand = "docker pull " + HUB_TAG+imageName;

            log.info("PULL Start");

            int pullExitCode = executeCommand(pullCommand);


            if (pullExitCode != 0) {
                return ResponseEntity.status(403).body(responseDto.builder().status("Fail").message("docker image pull error").build());
            }

            log.info("PULL Success");

            String updateService =strArr[0].substring("ptsd-".length()).trim();

            String getCurrentVersion = "docker service ps swarm_" + updateService +" --format {{.Image}} | tail -n 1";

            log.info("Update Service -> {}", updateService);

            String updateCommand = "docker service update --image " + HUB_TAG+imageName + " --update-parallelism 1 " + "swarm_"+updateService;

            log.info("UPDATE Start");

            int updateExitCode = executeCommand(updateCommand);

            if (updateExitCode != 0) {
                return ResponseEntity.status(403).body(responseDto.builder().status("Fail").message("docker rolling update fail").build());
            }

            log.info("UPDATE Success");

            log.info("Get Current Version Command -> {}", getCurrentVersion);

            log.info("Get Current Version Start");

            int getExitCode = executeCommand(getCurrentVersion);

            if (getExitCode != 0) {
                return ResponseEntity.status(403).body(responseDto.builder().status("Fail").message("docker error").build());
            }

            log.info("Get Current Version Success");

            String rmiCommand = "docker rmi -f $("+getCurrentVersion+")";

            log.info("Delete Image Start");

            int rmiExitCode = executeCommand(rmiCommand);

            System.out.println("rmiCommand = "+rmiCommand);
            if (rmiExitCode != 0) {
                return ResponseEntity.status(403).body(responseDto.builder().status("Fail").message("docker rmi error").build());
            }

            log.info("Delete Image Success");

            return ResponseEntity.ok().body(responseDto.builder().status("Success").message("Rolling Update 성공").build());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(403).body(responseDto.builder().status("Error").message("오류 발생").build());
        }



    }

    @Override
    public ResponseEntity<responseDto> scaleOut() throws IOException, InterruptedException {

        int exitCode;
        int tot;
        int replica;
        if (cnt % 2 == 1) {
            String serviceName = "swarm_ly";
            String serviceName2 = "swarm_auth-server";
            tot = countRunningReplicas(serviceName);
            log.info("TOT -> {}", tot);
            replica = tot + 1;
            String command = "docker service scale "+serviceName+"="+replica;
            log.info("Command -> {}", command);

            exitCode = executeCommand(command);

            if (exitCode != 0) {
                return ResponseEntity.status(403).body(responseDto.builder().status("Fail").message("Error").build());
            }


            tot = countRunningReplicas(serviceName2);
            log.info("TOT -> {}", tot);
            replica = tot + 1;
            command = "docker service scale "+serviceName2+"="+replica;
            log.info("Command -> {}", command);
            exitCode = executeCommand(command);

            if (exitCode != 0) {
                return ResponseEntity.status(403).body(responseDto.builder().status("Fail").message("Error").build());
            }

        } else {
            String serviceName = "swarm_ly";
            tot = countRunningReplicas(serviceName);
            log.info("TOT -> {}", tot);
            replica = tot + 1;
            String command = "docker service scale "+serviceName+"="+replica;
            log.info("Command -> {}", command);
            exitCode = executeCommand(command);

            if (exitCode != 0) {
                return ResponseEntity.status(403).body(responseDto.builder().status("Fail").message("Error").build());
            }

            if (exitCode != 0) {
                return ResponseEntity.status(403).body(responseDto.builder().status("Fail").message("Error").build());
            }
        }

        cnt++;

        return ResponseEntity.status(200).body(responseDto.builder().status("Success").message("Scale Out 성공").build());
    }

    private int executeCommand(String command) throws IOException, InterruptedException {
        log.info("Command -> {}", command);
        ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", command);

        pb.redirectErrorStream(true);
        Process process = pb.start();

        log.info("Process Starting");

        process.waitFor();

        BufferedReader br = new BufferedReader(new InputStreamReader( process.getInputStream() ));

        String line = null;
        while( (line = br.readLine()) != null ){
            log.info(line);
        }

        if(process != null) {
            process.destroy();
        }

        log.info("Command Done");

        // 명령의 종료 코드 반환
        return process.exitValue();
    }

    public static int countRunningReplicas(String serviceName) {

        String command = "docker service ps "+serviceName+" --no-trunc";

        log.info("COMMAND -> {}", command);

        int runningCount = 0;

        try {
            Process process = new ProcessBuilder("/bin/bash", "-c", command)
                    .start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.contains("Running")) {
                    runningCount++;
                }
            }

            process.waitFor();
            return runningCount;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
