package common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkGroupMember {
    public int GROUP_ID;
    public String GROUP_NAME;
    public int GROUP_ORDER;
    public String REG_DAY;
    public String CHANGE_DAY;

    public static int groupId;
}
