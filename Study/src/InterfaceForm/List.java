package InterfaceForm;

public interface List<E> {
    boolean add(E value);
    /*
        리스트에 요소 추가

        @param value 리스트에 추가할 요소
	    @return 중복 O -> false 반환, 중복 X -> true 반환
     */

    void add(int index, E value);
    /*
        리스트의 index 위치에 요소 추가

        @param index 리스트에 요소를 추가할 특정 위치 변수
        @param value 리스트에 추가할 요소
     */

    E remove(int index);
    /*
        리스트의 index 위치에 있는 요소 삭제
        @param index 리스트에서 삭제할 위치 변수
        @return 삭제된 요소 반환
     */

    boolean remove(Object value);
    /*
            리스트의 특정 요소 삭제, 동일한 요소 여러 개일 경우 가장 처음 발견한 요소만 삭제
            @param value 리스트에서 삭제할 요소
            @return 리스트에 삭제할 요소가 없거나 삭제 실패 시 false 반환, 삭제 성공 시 true 반환
     */

    E get(int index);
    /*
        리스트의 index 위치에 있는 요소 반환
        @param index 리스트에 접근할 위치 변수
        @return 리스트의 index에 위치에 있는 요소 반환
     */

    void set(int index, E value);
    /*
            리스트의 특정 위치에 있는 요소를 새 요소로 대체
            @param index 리스트에 접근할 위치 변수
            @param value 새로 대체할 요소 변수
     */

    boolean contains(Object value);
    /*
            특정 요소가 리스트에 있는 여부 확인
            @param value 리스트에서 찾을 특정 요소 변수
            @return 리스트에 특정 요소 존재할 경우 true 반환, 존재하지 않을 경우 false 반환
     */

    int indexOf(Object value);
    /*
            특정 요소가 리스트의 몇 번째 위치에 있는지 반환
            @param value 리스트에서 찾을 요소 변수
            @return 요소와 일치하는 위치 반환, 존재하지 않을 경우 -1 반환
     */

    int size();
    /*
            리스트에 있는 요소 개수 반환

            @return 리스트에 있는 요소 개수 반환
     */

    boolean isEmpty();
    /*
            리스트에 요소가 비어있는지 반환

            @return 요소 없을 시 true 반환, 있으면 false 반환
     */

    public void clear();
    /*
        리스트에 있는 모든 요소 삭제
     */
}
