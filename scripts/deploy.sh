#!/bin/bash

# 변수 선언
REPOSITORY=~/app/step2
EXECUTABLE_NAME=miniProj


echo "> Build 파일 복사"

cp $REPOSITORY/zip/*.jar $REPOSITORY

echo "> 현재 구동중인 애플리케이션 pid 확인"

# 명령어 실행결과를 변수에 할당 $()
CURRENT_PID=$(pgrep -fl ${EXECUTABLE_NAME} | grep java | awk '{print $1}')

echo "> 현재 구동중인 애플리케이션 pid: $CURRENT_PID"

# [ -z ] 는 문자열의 길이가 0이면 참
if [ -z "$CURRENT_PID" ]; then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> 새 애플리케이션 배포"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | grep -v plain | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

# nohup은 터미널이 끊겨도 실행한 프로세스는 계속 동작하게 함. 실행 중 생기는 메시지를 nohup.out 파일을 만들어 이 파일에 출력
# 2>&1는 표준오류(stderr)를 표준출력(stdout)이 전달되는 곳으로 보냄. 따라서 nohup.out에 표준오류도 같이 씀
# 마지막 &는 명령을 백그라운드 작업으로 실행함
nohup java -jar $JAR_NAME --spring.profiles.active=dev > $REPOSITORY/nohup.out 2>&1 &