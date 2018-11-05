package org.walkframework.console.tools.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.walkframework.base.system.common.Common;
import org.walkframework.base.system.factory.SingletonFactory;
import org.walkframework.console.mvc.service.base.BaseConsoleService;

/**
 * 可序列化对象编码/解码工具类
 * 
 * @author shf675
 *
 */
public class HexSerializableUtil {
	
	protected static Common common = SingletonFactory.getInstance(Common.class);
	
	private final static JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer(BaseConsoleService.class.getClassLoader());
	
	/**
	 * 编码
	 * 
	 * 正向：先序列化，后编码
	 * @param object
	 * @return
	 */
	public static String encodeHex(Object object){
		try {
			return Hex.encodeHexString(serializer.serialize(object));
		} catch (SerializationException e) {
			if(object != null){
				common.error(String.format("类[%s]未实现java.io.Serializable接口", object.getClass().getName()), e);
			}
		}
		return null;
	}
	
	/**
	 * 解码
	 * 
	 * 反向：先解码，后序列化
	 * @param hexstr
	 * @return
	 * @throws DecoderException
	 */
	public static Object decodeHex(String hexstr) throws DecoderException{
		return serializer.deserialize(Hex.decodeHex(hexstr.toCharArray()));
	}
	
	public static void main(String[] args) throws DecoderException {
		//测试
		testHex2();
	}
	
	/**
	 * 测试hex
	 * 
	 * @throws DecoderException
	 */
	public static void testHex() throws DecoderException{
		//对象
		//Integer object = 12345;
		Map<String, Object> object = new HashMap<String, Object>();
		object.put("key1", "value1");
		
		//编码
		String hexstr = HexSerializableUtil.encodeHex(object);
		System.out.println(hexstr);
		
		//解码
		Object deObject = HexSerializableUtil.decodeHex(hexstr);
		System.out.println(deObject);
	}
	
	
	/**
	 * 测试hex
	 * 
	 * @throws DecoderException
	 */
	public static void testHex2() throws DecoderException{
		String hexstr = "aced00057372002a6f72672e6170616368652e736869726f2e73657373696f6e2e6d67742e53696d706c6553657373696f6e9d1ca1b8d58c626e0300007870770200db74002437376134303461622d626339612d343034362d623139342d3330353830346339356565317372000e6a6176612e7574696c2e44617465686a81014b597419030000787077080000015f3d2f0efc787371007e000377080000015f3d5024067877130000000001b7740000093132372e302e302e31737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c770800000010000000037400506f72672e6170616368652e736869726f2e7375626a6563742e737570706f72742e44656661756c745375626a656374436f6e746578745f41555448454e544943415445445f53455353494f4e5f4b4559737200116a6176612e6c616e672e426f6f6c65616ecd207280d59cfaee0200015a000576616c756578700174004d6f72672e6170616368652e736869726f2e7375626a6563742e737570706f72742e44656661756c745375626a656374436f6e746578745f5052494e434950414c535f53455353494f4e5f4b4559737200326f72672e6170616368652e736869726f2e7375626a6563742e53696d706c655072696e636970616c436f6c6c656374696f6ea87f5825c6a3084a0300014c000f7265616c6d5072696e636970616c7374000f4c6a6176612f7574696c2f4d61703b7870737200176a6176612e7574696c2e4c696e6b6564486173684d617034c04e5c106cc0fb0200015a000b6163636573734f726465727871007e00063f4000000000000c7708000000100000000174003b636f6d2e61736961696e666f2e77616c6b2e6578616d706c652e73797374656d2e73656375726974792e7265616c6d2e557365725265616c6d5f30737200176a6176612e7574696c2e4c696e6b656448617368536574d86cd75a95dd2a1e020000787200116a6176612e7574696c2e48617368536574ba44859596b8b7340300007870770c000000103f4000000000000173720042636f6d2e61736961696e666f2e77616c6b2e6578616d706c652e73797374656d2e73656375726974792e7072696e636970616c2e53746166665072696e636970616c00000000000000010200244c000861726561436f64657400124c6a6176612f6c616e672f537472696e673b4c0009617265614c6576656c71007e00164c0008617265614e616d6571007e00164c0017627373446576656c6f7065724368616e6e656c436f646571007e00164c0010627373446576656c6f706572436f646571007e00164c000c636173436c69656e7455726c71007e00164c000c63617353657276657255726c71007e00164c000663617374676371007e00164c000d63627373436865636b436f646571007e00164c000c6362737350617373776f726471007e00164c000863697479436f646571007e00164c000663697479496471007e00164c0008636974794e616d6571007e00164c000e6375634368616e6e656c5479706571007e00164c000963756343697479496471007e00164c000b637563446570617274496471007e00164c0017637563446576656c6f7065724368616e6e656c436f646571007e00164c0010637563446576656c6f706572436f646571007e00164c000e63756345706172636879436f646571007e00164c00066465706172747400304c636f6d2f61736961696e666f2f77616c6b2f6578616d706c652f6d76632f656e746974792f54644d4465706172743b4c000a646570617274436f646571007e00164c0008646570617274496471007e00164c000a6465706172744e616d6571007e00164c000d646576656c6f7065724e616d6571007e00164c000b65706172636879436f646571007e00164c000b657061726368794e616d6571007e00164c00076a6f62436f646571007e00164c00086a6f624c6576656c71007e00164c000c70726f76696e6365436f646571007e00164c000a70726f76696e6365496471007e00164c0004726f6c6571007e00164c000c73657269616c4e756d62657271007e00164c00077374616666496471007e00164c000973746166664e616d6571007e00164c00087465616d436f646571007e00164c00087465616d4e616d6571007e0016787200356f72672e77616c6b6672616d65776f726b2e736869726f2e61757468632e7072696e636970616c2e426173655072696e636970616c00000000000000010200045a000766726f6d4361734c0005746f6b656e74002f4c6f72672f77616c6b6672616d65776f726b2f736869726f2f61757468632f746f6b656e2f42617365546f6b656e3b4c000675736572496471007e00164c0008757365724e616d6571007e00167870007372002d6f72672e77616c6b6672616d65776f726b2e736869726f2e61757468632e746f6b656e2e466f726d546f6b656ed4b67c0d78a187750200055a000775736553616c744c000870617373776f726471007e00164c001170617373776f7264456e63727970746f727400394c6f72672f77616c6b6672616d65776f726b2f736869726f2f7574696c2f70617373776f72642f50617373776f7264456e63727970746f723b4c000473616c7471007e00164c0008757365726e616d6571007e00167872002d6f72672e77616c6b6672616d65776f726b2e736869726f2e61757468632e746f6b656e2e42617365546f6b656e00000000000000010200035a000c697352656d656d6265724d654c0004686f737471007e00164c00097072696e636970616c7400374c6f72672f77616c6b6672616d65776f726b2f736869726f2f61757468632f7072696e636970616c2f426173655072696e636970616c3b7870007400093132372e302e302e3171007e001a01740001317372003e6f72672e77616c6b6672616d65776f726b2e736869726f2e7574696c2e70617373776f72642e44656661756c7450617373776f7264456e63727970746f7200000000000000010200007870707400084147594b463030317400084147594b4630303170707070707070707070707400043038353170707070707070707372002e636f6d2e61736961696e666f2e77616c6b2e6578616d706c652e6d76632e656e746974792e54644d446570617274000000000000000102000078720034636f6d2e61736961696e666f2e77616c6b2e6578616d706c652e6d76632e656e746974792e54644d4465706172744d61726b6574000000000000000102001d4c000861726561436f646571007e00164c000a646570617274436f646571007e00164c000b6465706172744672616d6571007e00164c0008646570617274496471007e00164c000e6465706172744b696e64436f646571007e00164c000b6465706172744c6576656c7400164c6a6176612f6d6174682f426967446563696d616c3b4c000a6465706172744e616d6571007e00164c000a6465706172745479706571007e00164c0007656e64446174657400104c6a6176612f7574696c2f446174653b4c000b65706172636879436f646571007e00164c000b6d6170446570617274496471007e00164c00076f726465724e6f71007e00294c000e706172656e74446570617274496471007e00164c000c7265616c4b696e64436f646571007e00164c000672656d61726b71007e00164c0008727376616c75653171007e00164c0008727376616c75653271007e00164c0008727376616c75653371007e00164c0008727376616c75653471007e00164c000d7365676d656e744d61726b657471007e00164c000973746172744461746571007e002a4c00097465616d4c6576656c71007e00294c00077465616d54616771007e00164c00087465616d5479706571007e00164c000e757064617465446570617274496471007e00164c000d7570646174655374616666496471007e00164c000a75706461746554696d6571007e002a4c000e75736572446570617274436f646571007e00164c000976616c6964666c616771007e0016787200286f72672e77616c6b6672616d65776f726b2e646174612e656e746974792e42617365456e746974796444e6f919e631460200025a000e5f494d504f52545f524553554c544c000d5f494d504f52545f4552524f5271007e00167872002c6f72672e77616c6b6672616d65776f726b2e646174612e656e746974792e4162737472616374456e74697479c384ae66b8d6b7c20200014c000b6f706572436f6c756d6e7371007e000d78707371007e000f3f400000000000187708000000200000000f74000956414c4944464c4147737200286f72672e77616c6b6672616d65776f726b2e646174612e656e746974792e4f706572436f6c756d6e00000000000000010200075a000b6973436f6e646974696f6e4c0009636f6e646974696f6e7400294c6f72672f77616c6b6672616d65776f726b2f646174612f656e746974792f436f6e646974696f6e3b4c000a6f706572436f6c756d6e71007e00164c00126f706572436f6c756d6e50726f706572747971007e00164c000e6f706572436f6c756d6e547970657400114c6a6176612f6c616e672f436c6173733b4c000f6f706572436f6c756d6e56616c75657400124c6a6176612f6c616e672f4f626a6563743b4c0004736f727471007e00167870007071007e002f74000976616c6964666c6167767200106a6176612e6c616e672e537472696e67a0f0a4387a3bb34202000078707400013070740010504152454e545f4445504152545f49447371007e0030007071007e003974000e706172656e74446570617274496471007e003774000448424355707400104445504152545f4b494e445f434f44457371007e0030007071007e003d74000e6465706172744b696e64436f646571007e00377400035a5a5a707400085445414d5f5441477371007e0030007071007e00417400077465616d54616771007e00377400013070740009415245415f434f44457371007e0030007071007e004574000861726561436f646571007e0037740004303835317074000b4445504152545f545950457371007e0030007071007e004974000a6465706172745479706571007e0037740001317074000b4445504152545f4e414d457371007e0030007071007e004d74000a6465706172744e616d6571007e0037740009e6ada6e6b189e5b882707400094445504152545f49447371007e0030007071007e0051740008646570617274496471007e0037740004303835317074000c4445504152545f4652414d457371007e0030007071007e005574000b6465706172744672616d6571007e0037740004303835317074000c4445504152545f4c4556454c7371007e0030007071007e005974000b6465706172744c6576656c767200146a6176612e6d6174682e426967446563696d616c54c71557f981284f0300024900057363616c654c0006696e7456616c7400164c6a6176612f6d6174682f426967496e74656765723b787200106a6176612e6c616e672e4e756d62657286ac951d0b94e08b02000078707371007e005c00000000737200146a6176612e6d6174682e426967496e74656765728cfc9f1fa93bfb1d030006490008626974436f756e744900096269744c656e67746849001366697273744e6f6e7a65726f427974654e756d49000c6c6f776573745365744269744900067369676e756d5b00096d61676e69747564657400025b427871007e005efffffffffffffffffffffffefffffffe00000001757200025b42acf317f8060854e00200007870000000010278787074000b4445504152545f434f44457371007e0030007071007e006674000a646570617274436f646571007e00377400045a5a5a5a707400084f524445525f4e4f7371007e0030007071007e006a7400076f726465724e6f71007e005f7371007e005c000000007371007e0061fffffffffffffffffffffffefffffffe000000017571007e0064000000010b787870740008525356414c5545327371007e0030007071007e0070740008727376616c75653271007e0037740004303835317074000c455041524348595f434f44457371007e0030007071007e007474000b65706172636879436f646571007e0037740004303835317074000a53544152545f444154457371007e0030007071007e00787400097374617274446174657671007e00037371007e0003770800000140e5be137078707800017071007e004871007e006971007e005871007e005471007e004071007e006071007e005071007e004c7071007e00777071007e006d71007e003c70707071007e007370707071007e007c7071007e0044707070707071007e0038707070707400043038353170707070707074000b313836313131313131313171007e0025740020e7bb88e5aea1e7aea1e79086e5919828e9ab98e7baa7e7aea1e79086e5919829707078780077010171007e0010787400135f43414348455f4b45595f4d41505f4e414d457371007e00063f4000000000000c770800000010000000017400013071007e0002787878";
		
		//解码
		SimpleSession deObject = (SimpleSession)HexSerializableUtil.decodeHex(hexstr);
		System.out.println(deObject.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)); 
	}
}