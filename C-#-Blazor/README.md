## C#
[C# 사이트에서 시작하기](https://docs.microsoft.com/ko-kr/dotnet/csharp/tour-of-csharp/)를 눌리고 읽어봄..
코드를 브라우저에서 치고, 실행시켜볼 수 있는 [자습서](https://docs.microsoft.com/ko-kr/dotnet/csharp/tutorials/exploration/interpolated-strings)를 발견. 기본 문법을 두둘겨보았다. 

### 1일차 소감
- C# 템플릿 스트링이 좀더 편리하네요 한번면 딸라쓰면 되다니
```
Console.WriteLine($"My friends are {firstFriend} and {secondFriend}");
```

- C#은 상수를 파스칼케이스로 선언하군요. 타이핑이 더 좋은것 같습니다.(예시 기준)
```
int max = int.MaxValue;
```

- 중간 표햔식 포맷팅 제어. 겁나 간단함  콜론(:)으로 포맷 스트링 붙이면. 뚱딱뚱딱
'd'는 짧은 표준 date 포맷, 'C2'는 숫자 자릴수 표현. C2는 소숫점 2자리까지 표현한다는 뜻이다.
```
var item = (Name: "eggplant", Price: 1.99m, perPackage: 3);
var date = DateTime.Now;

Console.WriteLine($"On {date}, the price of {item.Name} was {item.Price} per {item.perPackage} items.");
Console.WriteLine($"On {date:d}, the price of {item.Name} was {item.Price:C2} per {item.perPackage} items");
```

자습서가 이상한지 몰라고 소숫점 자리 출력물에 이상한 아이콘이 보여서 신기했다 이게 머선 129
<img src="https://github.com/accidentlywoo/C-#-Blazor/image/소숫점자리.png" width="30%" height="30%" display="inline-block" alt="그래프"/>


## 참고 사이트
- 공홈 시작하기 : https://docs.microsoft.com/ko-kr/dotnet/csharp/tour-of-csharp/
- 자습서 : https://docs.microsoft.com/ko-kr/dotnet/csharp/tutorials/exploration/interpolated-strings