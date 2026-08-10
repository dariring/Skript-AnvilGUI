# Skript-AnvilGUI

A Skript Addon that allows users to create Anvil GUIs to read user input.

- **Original Repository**: https://github.com/DereWah/Skript-AnvilGUI
- **Author's Fork**: https://github.com/Dere-Wah/Skript-AnvilGUI
- **Compatibility Fork (Skript 2.15+)**: https://github.com/dariring/Skript-AnvilGUI

---

## Languages
- [English](#english)
- [한국어](#한국어)

---

## English

### Compatibility
- Spigot / Paper 1.12 ~ 1.21.x (26.x)
- Skript 2.15+

### Tutorial & Docs
- Tutorial: https://skripthub.net/tutorials/77
- Documentation: http://skripthub.net/docs/?addon=Skript-AnvilGUI

### Important Notes
- **Skript 2.7+**: Use `virtual anvil` instead of `anvil gui` for event values (e.g. `event-virtual anvil`).
- **Skript 2.15+**: Use `anvil gui title of %virtualanvil%` and `anvil gui text of %virtualanvil%`. Skript 2.15+ claims generic `title of` and `text of` syntaxes.

### Syntax

#### Effects
- **Open Anvil GUI**
  ```skript
  open (anvilgui|anvil gui) %virtualanvil% to %players%
  ```

#### Types
- **Virtual Anvil**: `%virtualanvil%`, `virtual anvil`, `event-virtual anvil`

#### Events
- **Anvil GUI Close**
  ```skript
  on anvil gui close
  ```
  Values: `event-text`, `event-virtual anvil`

- **Anvil GUI Click**
  ```skript
  on anvil gui click
  ```
  Values: `event-integer` (slot index: 0=left, 1=right, 2=output), `event-text`, `event-virtual anvil`

#### Expressions
- **New Anvil GUI**
  ```skript
  new anvil gui
  [a] new anvil gui (named|with title) %string% with [default] text %string%
  ```
- **Anvil GUI Title**
  ```skript
  [the] anvil [gui] title of %virtualanvil%
  %virtualanvil%'[s] anvil [gui] title
  ```
- **Anvil GUI Text**
  ```skript
  [the] anvil [gui] text of %virtualanvil%
  %virtualanvil%'[s] anvil [gui] text
  ```
- **Item Slots**
  ```skript
  (left|right|output) (item|slot) of %virtualanvil%
  ```

### Example
```skript
command /password:
    trigger:
        set {_gui} to a new anvil gui named "&0Insert password" with default text "password"
        open anvil gui {_gui} to player

on anvil gui click:
    if event-integer is 2:
        if anvil gui title of event-virtual anvil is "&0Insert password":
            if event-text is "1234":
                close player's inventory
                send "&aCorrect password."
            else:
                set anvil gui text of event-virtual anvil to "Wrong password"
                open anvil gui event-virtual anvil to player
```

### FAQ
- **Output slot items are not set**: Known limitation of the underlying AnvilGUI library. Use left and right slots instead.

---

## 한국어

### 호환성
- Spigot / Paper 1.12 ~ 1.21.x (26.x)
- Skript 2.15+

### 튜토리얼 및 문서
- 튜토리얼: https://skripthub.net/tutorials/77
- 공식 문서: http://skripthub.net/docs/?addon=Skript-AnvilGUI

### 주의사항
- **Skript 2.7+**: 이벤트 구문에서 `anvil gui` 대신 `virtual anvil`을 사용하세요 (예: `event-virtual anvil`).
- **Skript 2.15+**: `title of`와 `text of` 구문을 Skript에서 점유하므로 `anvil gui title of %virtualanvil%` 및 `anvil gui text of %virtualanvil%`을 사용해야 합니다.

### 구문

#### 효과 (Effects)
- **모루 GUI 열기**
  ```skript
  open (anvilgui|anvil gui) %virtualanvil% to %players%
  ```

#### 타입 (Types)
- **Virtual Anvil**: `%virtualanvil%`, `virtual anvil`, `event-virtual anvil`

#### 이벤트 (Events)
- **모루 GUI 닫기**
  ```skript
  on anvil gui close
  ```
  이벤트 값: `event-text`, `event-virtual anvil`

- **모루 GUI 클릭**
  ```skript
  on anvil gui click
  ```
  이벤트 값: `event-integer` (슬롯 번호: 0=왼쪽, 1=오른쪽, 2=결과), `event-text`, `event-virtual anvil`

#### 표현식 (Expressions)
- **새 모루 GUI 생성**
  ```skript
  new anvil gui
  [a] new anvil gui (named|with title) %string% with [default] text %string%
  ```
- **모루 GUI 제목**
  ```skript
  [the] anvil [gui] title of %virtualanvil%
  %virtualanvil%'[s] anvil [gui] title
  ```
- **모루 GUI 입력 텍스트**
  ```skript
  [the] anvil [gui] text of %virtualanvil%
  %virtualanvil%'[s] anvil [gui] text
  ```
- **아이템 슬롯**
  ```skript
  (left|right|output) (item|slot) of %virtualanvil%
  ```

### 예제
```skript
command /password:
    trigger:
        set {_gui} to a new anvil gui named "&0비밀번호 입력" with default text "password"
        open anvil gui {_gui} to player

on anvil gui click:
    if event-integer is 2:
        if anvil gui title of event-virtual anvil is "&0비밀번호 입력":
            if event-text is "1234":
                close player's inventory
                send "&a비밀번호가 일치합니다."
            else:
                set anvil gui text of event-virtual anvil to "비밀번호 틀림"
                open anvil gui event-virtual anvil to player
```

### FAQ
- **결과 슬롯 아이템이 설정되지 않음**: 하위 AnvilGUI 라이브러리 한계로 인해 결과 슬롯 아이템은 서버에서 초기화됩니다. 왼쪽/오른쪽 슬롯을 활용하세요.
